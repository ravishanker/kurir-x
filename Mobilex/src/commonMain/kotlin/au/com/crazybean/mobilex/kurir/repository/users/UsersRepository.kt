package au.com.crazybean.mobilex.kurir.repository.users

import au.com.crazybean.mobilex.kurir.data.model.*
import au.com.crazybean.mobilex.foundation.repository.Repository
import au.com.crazybean.mobilex.kurir.repository.users.network.UsersNetworkSource

class UsersRepository(private val dbSource: UsersSource?,
                      private val networkSource: UsersSource? = UsersNetworkSource()) : Repository() {
    fun checkUser(mobile: String, email: String, callback: (Auth?) -> Unit) {
        execute(callback) {
            checkUser(mobile, email)
        }
    }

    fun login(name: String, password: String, callback: (Auth?) -> Unit) {
        execute(callback) {
            login(name, password)
        }
    }

    fun register(user: User, callback: (Auth?) -> Unit) {
        execute(callback) {
            register(user)
        }
    }

    private suspend fun checkUser(mobile: String, email: String): Auth {
        return dbSource?.getUser(mobile, email)?.let {
            Auth(ERR_EXISTS)
        }?: Auth(ERR_NOT_FOUND)
    }

    private suspend fun login(name: String, password: String): Auth {
        return dbSource?.getUser(name, name)?.let { user ->
            // Compare the password
            if (password == user.password) {
                // Login successfully
                Auth(ERR_NONE)
            } else {
                // Login failed
                Auth(ERR_PASSWORD)
            }
        }?: Auth(ERR_NOT_FOUND)
    }

    private suspend fun register(user: User): Auth {
        return dbSource?.getUser(user.mobile, user.email)?.let {
            Auth(ERR_EXISTS)
        }?: dbSource?.addUser(user)?.let {
            Auth(ERR_NONE)
        }?: Auth(ERR_UNKNOWN)
    }
}