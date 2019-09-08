package au.com.crazybean.mobilex.kurir.modules.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.kFirebaseToken
import au.com.crazybean.mobilex.kurir.data.model.Auth
import au.com.crazybean.mobilex.kurir.data.model.ERR_NOT_FOUND
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository
import au.com.crazybean.sdk.mvvm.ViewModel
import com.google.firebase.iid.FirebaseInstanceId

class SignupViewModel(private val userData: UserData?,
                      private val repository: UsersRepository?) : ViewModel() {

    private var user: User? = null

    override fun onRelease() {
        super.onRelease()
        repository?.onRelease()
    }

    fun signup(mobile: String,
               email: String): LiveData<Auth?> {
        return MutableLiveData<Auth?>().also { liveData ->
            repository?.checkUser(mobile, email) { auth ->
                if (auth?.result == ERR_NOT_FOUND) {
                    getUserToken { token ->
                        user = User(mobile = mobile, email = email, userToken = token)
                        liveData.value = auth
                    }
                } else {
                    liveData.value = auth
                }
            }
        }
    }

    fun register(firstName: String,
                 lastName: String,
                 password: String): LiveData<Auth?> {
        return MutableLiveData<Auth?>().also { liveData ->
            user?.let {
                it.firstName = firstName
                it.lastName = lastName
                it.password = password
                repository?.register(it) { auth ->
                    liveData.value = auth
                }
            }
        }
    }

    private fun getUserToken(callback: (String?)-> Unit) {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result?.token ?: ""
                    userData?.setString(token, kFirebaseToken)
                }

                callback(userData?.getString(kFirebaseToken, ""))
            }
    }
}