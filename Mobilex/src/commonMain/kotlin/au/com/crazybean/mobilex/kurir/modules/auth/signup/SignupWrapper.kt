package au.com.crazybean.mobilex.kurir.modules.auth.signup

import au.com.crazybean.mobilex.foundation.saw.Wrapper
import au.com.crazybean.mobilex.foundation.saw.pulse.LiveData
import au.com.crazybean.mobilex.kurir.data.model.Auth
import au.com.crazybean.mobilex.kurir.data.model.ERR_NOT_FOUND
import au.com.crazybean.mobilex.kurir.data.model.Enroll
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class SignupWrapper(private val repository: UsersRepository?) : Wrapper() {
    var enroll: Enroll? = null
    private set

    override fun onRelease() {
        super.onRelease()
        repository?.onRelease()
    }

    fun signup(mobile: String, email: String): LiveData<Auth?> {
        return LiveData<Auth?>().also { result ->
            repository?.checkUser(mobile, email) { auth ->
                if (auth?.result == ERR_NOT_FOUND) {
                    getUserToken { token ->
                        enroll = Enroll(email, mobile, token)
                        repository.signup(enroll!!) {
                            result.value = it
                        }
                    }
                } else {
                    result.value = auth
                }
            }
        }
    }

    fun register(firstName: String, lastName: String, password: String): LiveData<Auth?> {
        return LiveData<Auth?>().also { result ->
            enroll?.let {
                val user = User(mobile = it.mobile, email = it.email, firstName = firstName, lastName = lastName, password = password)
                repository?.register(user) { auth ->
                    result.value = auth
                }
            }
        }
    }

    private fun getUserToken(callback: (String?)-> Unit) {
        /*
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result?.token ?: ""
                    userData?.setString(token, kFirebaseToken)
                }

                callback(userData?.getString(kFirebaseToken, ""))
            }*/
    }
}