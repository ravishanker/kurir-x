package au.com.crazybean.mobilex.kurir.modules.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.crazybean.mobilex.data.model.Auth
import au.com.crazybean.mobilex.modules.auth.AuthWrapper
import au.com.crazybean.sdk.mvvm.ViewModel

class AuthViewModel(private val wrapper: AuthWrapper) : ViewModel() {
    fun login(name: String, password: String): LiveData<Auth?> {
        return MutableLiveData<Auth?>().also { liveData ->
            wrapper.login(name, password) {
                liveData.value = it
            }
        }
    }
}