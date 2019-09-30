package au.com.crazybean.mobilex.kurir.modules.tasks.impl

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import au.com.crazybean.foundation.widgets.RecyclerUtils
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.data.model.Task
import au.com.crazybean.mobilex.kurir.modules.base.Module
import au.com.crazybean.mobilex.kurir.modules.base.RecyclerFragment
import au.com.crazybean.mobilex.kurir.modules.tasks.TasksDelegate
import au.com.crazybean.mobilex.kurir.modules.tasks.TasksView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class TasksFragment : RecyclerFragment<TasksDelegate, Task>(), TasksView {
    override val delegate: TasksDelegate? by inject {
        parametersOf(this)
    }

    override val adapter: RecyclerUtils.Adapter<Task>? by lazy {
        TasksAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tasks, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_new_task -> {
                delegate?.onNewTask()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onEntitySelect(entity: Task, position: Int) {
        super.onEntitySelect(entity, position)
        delegate?.onViewDetail(entity)
    }

    override fun onRefresh() {
        delegate?.onRefresh()

    }

    override fun showTasks(tasks: List<Task>) {
        adapter?.addEntities(tasks)
    }

    override fun showEmpty() {
    }

    override fun showCreation() {
        navigate(Module.Creation)
    }
}