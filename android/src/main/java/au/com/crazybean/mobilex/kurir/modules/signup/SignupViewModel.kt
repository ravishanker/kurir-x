package au.com.crazybean.mobilex.kurir.modules.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.crazybean.mobilex.data.model.Auth
import au.com.crazybean.mobilex.repository.auth.AuthRepository
import au.com.crazybean.sdk.mvvm.ViewModel

class SignupViewModel(private val repository: AuthRepository?) : ViewModel() {
    override fun onRelease() {
        super.onRelease()
        repository?.onRelease()
    }

    fun register(name: String,
                 password: String,
                 email: String,
                 firstName: String,
                 lastName: String): LiveData<Auth?> {
        return MutableLiveData<Auth?>().also { liveData ->
            repository?.login(name, password) {
                liveData.value = it
            }
        }
    }
}