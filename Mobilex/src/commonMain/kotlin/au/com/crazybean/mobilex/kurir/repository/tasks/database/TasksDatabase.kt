package au.com.crazybean.mobilex.kurir.repository.tasks.database

import au.com.crazybean.mobilex.kurir.data.model.Task
import au.com.crazybean.mobilex.kurir.database.Database
import au.com.crazybean.mobilex.kurir.repository.tasks.TasksSource

class TasksDatabase(private val database: Database) : TasksSource {
    override suspend fun getTasks(): List<Task> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun update(task: Task): Task? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun delete(task: Task): Task? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun create(task: Task): Task? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}