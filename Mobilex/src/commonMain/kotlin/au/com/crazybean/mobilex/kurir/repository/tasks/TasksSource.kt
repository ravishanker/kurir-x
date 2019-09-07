package au.com.crazybean.mobilex.kurir.repository.tasks

import au.com.crazybean.mobilex.kurir.data.model.Task

interface TasksSource {
    suspend fun getTasks(): List<Task>
    suspend fun update(task: Task): Task?
    suspend fun delete(task: Task): Task?
    suspend fun create(task: Task): Task?
}