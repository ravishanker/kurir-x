package au.com.crazybean.mobilex.kurir.repository.users

import au.com.crazybean.mobilex.kurir.data.model.*
import au.com.crazybean.mobilex.foundation.repository.Repository

class UsersRepository(private val usersSource: UsersSource?) : Repository() {
    fun checkUser(mobile: String, email: String, callback: (Auth?) -> Unit) {
        usersSource?.getUser(mobile, email) { user ->
            (user?.let {
                ERR_EXISTS
            }?: ERR_NOT_FOUND).let { result ->
                callback(Auth(result))
            }
        }
    }

    fun login(name: String, password: String, callback: (Auth?) -> Unit) {
        usersSource?.getUser(name, name) { user ->
            (user?.let {
                if (password == it.password) ERR_NONE else ERR_PASSWORD
            }?: ERR_NOT_FOUND).let { result ->
                callback(Auth(result))
            }
        }
    }

    fun register(user: User, callback: (Auth?) -> Unit) {
        usersSource?.getUser(user.mobile, user.email) {
            it?.let {
                callback(Auth(ERR_EXISTS))
            }?: usersSource.addUser(user) { result ->
                result?.let {
                    callback(Auth(ERR_NONE))
                }?: callback(Auth(ERR_UNKNOWN))
            }
        }
    }
}