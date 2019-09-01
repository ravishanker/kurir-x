package au.com.crazybean.mobilex.repository.auth

import au.com.crazybean.mobilex.data.model.*
import au.com.crazybean.mobilex.repository.Repository
import au.com.crazybean.mobilex.repository.auth.database.UsersDatabase

class AuthRepository(private val database: UsersDatabase) : Repository() {
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

    private suspend fun login(name: String, password: String): Auth? {
        return database.getUser(name)?.let { user ->
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

    private suspend fun register(user: User): Auth? {
        return database.getUser(user.email?: "")?.let {
            Auth(ERR_EXISTS)
        }?: database.addUser(user)?.let {
            Auth(ERR_NONE)
        }
    }
}