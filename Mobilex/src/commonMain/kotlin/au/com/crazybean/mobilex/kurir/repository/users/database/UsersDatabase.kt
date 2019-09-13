package au.com.crazybean.mobilex.kurir.repository.users.database

import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.kurir.database.Database
import au.com.crazybean.mobilex.kurir.repository.users.UsersSource

private const val kEmail = "email"
private const val kMobile = "mobile"
private const val kPassword = "password"
private const val kFirstName = "first_name"
private const val kLastName = "last_name"
private const val kDeviceToken = "device_token"

private const val TABLE_NAME = "users"

class UsersDatabase(private val database: Database) : UsersSource {
    override fun getUser(mobile: String?, email: String?, callback: (User?) -> Unit) {
        database.readData(TABLE_NAME, onSuccess = { entities ->
            entities.firstOrNull { map ->
                (map[kEmail] as String?)?.equals(email, true)?: false || (map[kMobile] as String?)?.equals(mobile, true)?: false
            }?.let { payload ->
                callback(payload.toUser)
            }?: callback(null)
        }, onError = { throwable ->
            Logger.d(throwable)
            callback(null)
        })
    }

    override fun addUser(user: User, callback: (User?) -> Unit) {
        database.writeData(TABLE_NAME, user.toMap, onSuccess = {
            callback(user)
        }, onError = { throwable ->
            Logger.d(throwable)
            callback(null)
        })
    }

    private val Map<String, Any?>.toUser: User
        get() = User(
            get(kEmail) as String?,
            get(kMobile) as String?,
            get(kPassword) as String?,
            get(kFirstName) as String?,
            get(kLastName) as String?,
            get(kDeviceToken) as String?
        )

    private val User.toMap: Map<String, Any?>
        get() = mapOf(
            Pair(kEmail, email),
            Pair(kMobile, mobile),
            Pair(kPassword, password),
            Pair(kFirstName, firstName),
            Pair(kLastName, lastName),
            Pair(kDeviceToken, userToken)
        )
}