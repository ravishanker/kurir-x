package au.com.crazybean.mobilex.kurir.modules.tasks

import au.com.crazybean.mobilex.foundation.saw.Wrapper
import au.com.crazybean.mobilex.foundation.saw.pulse.LiveData
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.model.Task
import au.com.crazybean.mobilex.kurir.repository.tasks.TasksRepository
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class TasksWrapper(private val userData: UserData?,
                   private val usersRepository: UsersRepository?,
                   private val tasksRepository: TasksRepository?) : Wrapper() {

    val tasks: LiveData<List<Task>?>
        get() = LiveData<List<Task>?>().also { result ->
            tasksRepository?.getTasks { tasks ->
                result.value = tasks
            }
        }
}