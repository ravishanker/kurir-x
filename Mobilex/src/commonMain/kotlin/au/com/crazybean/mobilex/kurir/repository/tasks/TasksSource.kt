package au.com.crazybean.mobilex.kurir.repository.tasks

import au.com.crazybean.mobilex.kurir.data.model.Task

interface TasksSource {
    fun getTasks(picker: String? = null, completion: (List<Task>?) -> Unit)
    fun pickTask(task: Task, picker: String, completion: (Boolean) -> Unit)
    fun createTask(task: Task, forPicker: String? = null, completion: (Boolean) -> Unit)
    fun deleteTask(task: Task, completion: (Boolean) -> Unit)
}