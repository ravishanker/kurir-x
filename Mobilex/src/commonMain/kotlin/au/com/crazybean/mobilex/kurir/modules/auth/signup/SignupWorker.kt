package au.com.crazybean.mobilex.kurir.modules.auth.signup

import au.com.crazybean.mobilex.foundation.saw.Worker
import au.com.crazybean.mobilex.foundation.saw.awareness.Emitter
import au.com.crazybean.mobilex.kurir.data.model.Auth
import au.com.crazybean.mobilex.kurir.data.model.ERR_NOT_FOUND
import au.com.crazybean.mobilex.kurir.data.model.Enroll
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class SignupWorker(private val repository: UsersRepository?) : Worker() {
    var enroll: Enroll? = null
    private set

    override fun onRelease() {
        super.onRelease()
        repository?.onRelease()
    }

    fun signup(mobile: String, email: String): Emitter<Auth?> {
        return Emitter<Auth?>().also { result ->
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

    fun register(firstName: String, lastName: String, password: String): Emitter<Auth?> {
        return Emitter<Auth?>().also { result ->
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