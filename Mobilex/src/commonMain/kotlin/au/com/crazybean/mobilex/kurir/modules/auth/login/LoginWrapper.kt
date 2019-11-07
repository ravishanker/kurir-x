package au.com.crazybean.mobilex.kurir.modules.auth.login

import au.com.crazybean.mobilex.foundation.saw.Wrapper
import au.com.crazybean.mobilex.foundation.saw.awareness.Emitter
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.kEmail
import au.com.crazybean.mobilex.kurir.data.kPassword
import au.com.crazybean.mobilex.kurir.data.model.Auth
import au.com.crazybean.mobilex.kurir.data.model.ERR_NONE
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class LoginWrapper(private val userData: UserData?,
                   private val repository: UsersRepository?) : Wrapper() {

    override fun onRelease() {
        super.onRelease()
        repository?.onRelease()
    }

    fun login(name: String, password: String): Emitter<Auth?> {
        return Emitter<Auth?>().also { result ->
            repository?.login(name, password) { auth ->
                result.value = auth

                auth?.user?.takeIf { auth.result == ERR_NONE }?.let {
                    // Save the login user name and password onto user data.
                    userData?.setString(it.email?: "", kEmail)
                    userData?.setString(it.password?: "", kPassword)
                }
            }
        }
    }
}