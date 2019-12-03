package au.com.crazybean.mobilex.kurir.modules.creation.receiver

import au.com.crazybean.mobilex.foundation.native.currentMillis
import au.com.crazybean.mobilex.foundation.saw.Wrapper
import au.com.crazybean.mobilex.foundation.saw.pulse.LiveData
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.kEmail
import au.com.crazybean.mobilex.kurir.data.model.Address
import au.com.crazybean.mobilex.kurir.data.model.Contact
import au.com.crazybean.mobilex.kurir.data.model.Parcel
import au.com.crazybean.mobilex.kurir.data.model.Task
import au.com.crazybean.mobilex.kurir.repository.tasks.TasksRepository

class ReceiverWrapper(private val userData: UserData?,
                      private val repository: TasksRepository?) : Wrapper() {
    fun createTask(origin: Address, dest: Address, parcel: Parcel, receiver: Contact): LiveData<Boolean> {
        return LiveData<Boolean>().also { result ->
            val owner = userData?.getString(kEmail, "")?: ""
            val task = Task(origin, dest, owner, parcel, currentMillis, null, receiver)
            repository?.createTask(task, null) {
                result.value = it
            }
        }
    }
}