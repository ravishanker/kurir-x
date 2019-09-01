package au.com.crazybean.mobilex.kurir.modules.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.crazybean.mobilex.data.model.Auth
import au.com.crazybean.mobilex.repository.auth.AuthRepository
import au.com.crazybean.sdk.mvvm.ViewModel

class LoginViewModel(private val repository: AuthRepository?) : ViewModel() {
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