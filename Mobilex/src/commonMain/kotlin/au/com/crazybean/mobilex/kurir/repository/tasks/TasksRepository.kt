package au.com.crazybean.mobilex.kurir.repository.tasks

import au.com.crazybean.mobilex.foundation.repository.Repository
import au.com.crazybean.mobilex.kurir.data.model.Task

class TasksRepository(private val tasksSource: TasksSource?): Repository() {
    fun getTasks(picker: String? = null, completion: (List<Task>?) -> Unit) {
        tasksSource?.getTasks(picker, completion)
    }

    fun pickTask(task: Task, picker: String, completion: (Boolean) -> Unit) {
        tasksSource?.pickTask(task, picker, completion)
    }

    fun createTask(task: Task, forPicker: String? = null, completion: (Boolean) -> Unit) {
        tasksSource?.createTask(task, forPicker, completion)
    }

    fun deleteTask(task: Task, completion: (Boolean) -> Unit) {
        tasksSource?.deleteTask(task, completion)
    }
}