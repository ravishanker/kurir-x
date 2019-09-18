package au.com.crazybean.mobilex.kurir.modules.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.crazybean.foundation.mvvm.ViewModel
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.kEmail
import au.com.crazybean.mobilex.kurir.data.kPassword
import au.com.crazybean.mobilex.kurir.data.model.Auth
import au.com.crazybean.mobilex.kurir.data.model.ERR_NONE
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class LoginViewModel(private val userData: UserData?,
                     private val repository: UsersRepository?) : ViewModel() {
    override fun onRelease() {
        super.onRelease()
        repository?.onRelease()
    }

    fun login(name: String, password: String): LiveData<Auth?> {
        return MutableLiveData<Auth?>().also { liveData ->
            repository?.login(name, password) { auth ->
                liveData.value = auth

                auth?.user?.takeIf { auth.result == ERR_NONE }?.let {
                    // Save the login user name and password onto user data.
                    userData?.setString(it.email?: "", kEmail)
                    userData?.setString(it.password?: "", kPassword)
                }
            }
        }
    }
}