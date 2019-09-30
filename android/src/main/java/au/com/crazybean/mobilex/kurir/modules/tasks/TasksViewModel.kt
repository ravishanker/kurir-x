package au.com.crazybean.mobilex.kurir.modules.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.crazybean.foundation.mvvm.ViewModel
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.model.Task
import au.com.crazybean.mobilex.kurir.repository.tasks.TasksRepository
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class TasksViewModel(private val userData: UserData?,
                     private val usersRepository: UsersRepository?,
                     private val tasksRepository: TasksRepository?) : ViewModel() {

    val tasks: LiveData<List<Task>?>
        get() = MutableLiveData<List<Task>?>().also { liveData ->
            tasksRepository?.getTasks { tasks ->
                liveData.value = tasks
            }
        }
}