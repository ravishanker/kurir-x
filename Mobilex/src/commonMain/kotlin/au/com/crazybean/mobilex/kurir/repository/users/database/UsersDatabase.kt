package au.com.crazybean.mobilex.kurir.repository.users.database

import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.kurir.data.model.kEmail
import au.com.crazybean.mobilex.kurir.data.model.kMobile
import au.com.crazybean.mobilex.kurir.database.Database
import au.com.crazybean.mobilex.kurir.repository.users.UsersSource
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

private const val TABLE_NAME = "users"

class UsersDatabase(private val database: Database) :
    UsersSource {
    override suspend fun getUser(mobile: String?, email: String?): User? {
        return suspendCoroutine {
            database.readData(TABLE_NAME, onSuccess = { entities ->
                entities.firstOrNull { map ->
                    (map[kEmail] as String?)?.equals(email, true)?: false || (map[kMobile] as String?)?.equals(mobile, true)?: false
                }?.let { payload ->
                    it.resume(User.fromMap(payload))
                }?: it.resume(null)
            }, onError = { throwable ->
                Logger.d(throwable)
                it.resume(null)
            })
        }
    }

    override suspend fun addUser(user: User): User? {
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