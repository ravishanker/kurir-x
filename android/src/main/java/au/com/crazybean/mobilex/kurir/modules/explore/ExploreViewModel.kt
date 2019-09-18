package au.com.crazybean.mobilex.kurir.modules.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.crazybean.foundation.mvvm.ViewModel
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class ExploreViewModel(private val repository: UsersRepository?) : ViewModel() {
    private val liveData by lazy {
        MutableLiveData<List<User>?>()
    }

    fun getContacts(query: String?): LiveData<List<User>?> {
        return liveData.also {
            repository?.getUsers(null) { users ->
                it.value = users?.filter {
                   query.isNullOrEmpty() || it.firstName?.contains(query, true) == true || it.lastName?.contains(query, true) == true
                }
            }
        }
    }
}