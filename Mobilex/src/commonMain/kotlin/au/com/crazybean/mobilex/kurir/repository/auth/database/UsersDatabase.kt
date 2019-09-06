package au.com.crazybean.mobilex.kurir.repository.auth.database

import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.database.Database
import au.com.crazybean.mobilex.foundation.logger.Logger
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private const val TABLE_NAME = "users"

class UsersDatabase(private val database: Database) {
    suspend fun getUser(userName: String): User? {
        return suspendCoroutine {
            database.readData(TABLE_NAME, onSuccess = { entities ->
                entities.firstOrNull { map ->
                    (map["email"] as String?)?.equals(userName, true)?: false
                }?.let { payload ->
                    it.resume(User.fromMap(payload))
                }?: it.resume(null)
            }, onError = { throwable ->
                Logger.d(throwable)
                it.resume(null)
            })
        }
    }

    suspend fun addUser(user: User): User? {
        return suspendCoroutine {
            database.writeData(TABLE_NAME, user.payload, onSuccess = { _ ->
                it.resume(user)
            }, onError = { throwable ->
                Logger.d(throwable)
                it.resume(null)
            })
        }
    }
}