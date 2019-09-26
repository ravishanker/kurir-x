package au.com.crazybean.mobilex.kurir.modules.tasks

import au.com.crazybean.mobilex.kurir.data.model.Task
import au.com.crazybean.mobilex.kurir.modules.base.View

interface TasksView : View {
    fun showSpinner()
    fun hideSpinner()
    fun showTasks(tasks: List<Task>)
    fun showEmpty()
    fun showCreation()
}