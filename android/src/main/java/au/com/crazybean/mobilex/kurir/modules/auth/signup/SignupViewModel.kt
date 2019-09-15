package au.com.crazybean.mobilex.kurir.modules.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.crazybean.foundation.mvvm.ViewModel
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.kFirebaseToken
import au.com.crazybean.mobilex.kurir.data.model.Auth
import au.com.crazybean.mobilex.kurir.data.model.ERR_NOT_FOUND
import au.com.crazybean.mobilex.kurir.data.model.Enroll
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository
import com.google.firebase.iid.FirebaseInstanceId

class SignupViewModel(private val userData: UserData?,
                      private val repository: UsersRepository?) : ViewModel() {

    private var enroll: Enroll? = null

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
                        enroll = Enroll(email, mobile, token)
                        repository.signup(enroll!!) {
                            liveData.value = it
                        }
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
            enroll?.let {
                val user = User(mobile = it.mobile, email = it.email, firstName = firstName, lastName = lastName, password = password)
                repository?.register(user) { auth ->
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