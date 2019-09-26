package au.com.crazybean.mobilex.kurir.modules.tasks

import android.os.Bundle
import androidx.lifecycle.Observer
import au.com.crazybean.foundation.mvvm.Delegate

class TasksDelegate(view: TasksView?, viewModel: TasksViewModel) : Delegate<TasksView, TasksViewModel>(view, viewModel) {

    override fun onCreate(params: Bundle) {
        super.onCreate(params)
        onRefresh()
    }

    private fun onRefresh() {
        view?.showSpinner()
        viewModel.tasks
            .observe(this, Observer { tasks ->
                view?.hideSpinner()
                tasks?.takeIf { it.isNotEmpty() }?.let {
                    view?.showTasks(it)
                }?: view?.showEmpty()
            })
    }

    fun onNewTask() {
        view?.showCreation()
    }
}