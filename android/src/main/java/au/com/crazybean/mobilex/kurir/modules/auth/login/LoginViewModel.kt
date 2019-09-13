package au.com.crazybean.mobilex.kurir.modules.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.crazybean.foundation.mvvm.ViewModel
import au.com.crazybean.mobilex.kurir.data.model.Auth
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class LoginViewModel(private val repository: UsersRepository?) : ViewModel() {
    override fun onRelease() {
        super.onRelease()
        repository?.onRelease()
    }

    fun login(name: String, password: String): LiveData<Auth?> {
        return MutableLiveData<Auth?>().also { liveData ->
            repository?.login(name, password) {
                liveData.value = it
            }
        }
    }
}