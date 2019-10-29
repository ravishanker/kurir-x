package au.com.crazybean.mobilex.kurir.modules.tasks

import au.com.crazybean.mobilex.foundation.saw.Adviser
import au.com.crazybean.mobilex.kurir.data.model.Task

class TasksAdviser(scene: TasksScene?,
                   worker: TasksWorker) : Adviser<TasksScene, TasksWorker>(scene, worker) {
    override fun onLoad(params: Map<String, Any?>) {
        super.onLoad(params)
        onRefresh()
    }

    fun onRefresh() {
        scene?.showSpinner()
        worker.tasks.observe(this) { tasks ->
            scene?.hideSpinner()
                tasks?.takeIf { it.isNotEmpty() }?.let {
                    scene?.showTasks(it)
                }?: scene?.showEmpty()
            }
    }

    fun onNewTask() {
        scene?.showCreation()
    }

    fun onViewDetail(task: Task) {
    }
}