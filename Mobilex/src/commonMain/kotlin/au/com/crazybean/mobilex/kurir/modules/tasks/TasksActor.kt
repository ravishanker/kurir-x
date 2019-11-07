package au.com.crazybean.mobilex.kurir.modules.tasks

import au.com.crazybean.mobilex.foundation.saw.Actor
import au.com.crazybean.mobilex.kurir.data.model.Task

class TasksActor(scene: TasksScene?,
                 wrapper: TasksWrapper) : Actor<TasksScene, TasksWrapper>(scene, wrapper) {
    override fun onLoad(params: Map<String, Any?>) {
        super.onLoad(params)
        onRefresh()
    }

    fun onRefresh() {
        scene?.showSpinner()
        wrapper.tasks.observe(this) { tasks ->
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