package au.com.crazybean.mobilex.kurir.modules.tasks

import au.com.crazybean.mobilex.foundation.saw.Worker
import au.com.crazybean.mobilex.foundation.saw.awareness.Emitter
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.model.Task
import au.com.crazybean.mobilex.kurir.repository.tasks.TasksRepository
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class TasksWorker(private val userData: UserData?,
                  private val usersRepository: UsersRepository?,
                  private val tasksRepository: TasksRepository?) : Worker() {

    val tasks: Emitter<List<Task>?>
        get() = Emitter<List<Task>?>().also { result ->
            tasksRepository?.getTasks { tasks ->
                result.value = tasks
            }
        }
}