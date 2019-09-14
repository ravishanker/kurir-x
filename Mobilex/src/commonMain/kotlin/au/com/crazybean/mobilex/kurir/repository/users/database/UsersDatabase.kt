package au.com.crazybean.mobilex.kurir.repository.users.database

import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.foundation.native.currentMillis
import au.com.crazybean.mobilex.kurir.data.model.Enroll
import au.com.crazybean.mobilex.kurir.database.Database
import au.com.crazybean.mobilex.kurir.repository.users.UsersSource

private const val kEmail = "email"
private const val kMobile = "mobile"
private const val kPassword = "password"
private const val kFirstName = "first_name"
private const val kLastName = "last_name"
private const val kToken = "token"
private const val kTimestamp = "timestamp"
private const val kId = "id"

private const val TABLE_USERS = "users"
private const val TABLE_ENROLL = "enroll"

class UsersDatabase(private val database: Database) : UsersSource {
    override fun getUser(mobile: String?, email: String?, callback: (User?) -> Unit) {
        val filters = mobile?.takeIf { it.isNotBlank() }?.let {
            mapOf(Pair(kMobile, it))
        }?: email?.takeIf { it.isNotBlank() }?.let {
            mapOf(Pair(kEmail, it))
        }?: emptyMap()

        database.readData(TABLE_USERS, filters, onSuccess = { entities ->
            entities.firstOrNull()?.let {
                callback(it.toUser)
            }?: callback(null)
        }, onError = { throwable ->
            Logger.d(throwable)
            callback(null)
        })
    }

    override fun addUser(user: User, callback: (User?) -> Unit) {
        database.writeData(TABLE_USERS, null, user.toMap, onSuccess = {
            callback(user)
        }, onError = { throwable ->
            Logger.d(throwable)
            callback(null)
        })
    }

    override fun addEnroll(enroll: Enroll, callback: (Enroll?) -> Unit) {
        database.writeData(TABLE_ENROLL, null, enroll.toMap, onSuccess = {
            callback(enroll)
        }, onError = { throwable ->
            Logger.d(throwable)
            callback(null)
        })
    }

    override fun getEnroll(token: String, callback: (Enroll?) -> Unit) {
        database.readData(TABLE_ENROLL, null, onSuccess = { entities ->
            entities.firstOrNull { map ->
                (map[kToken] as String?)?.equals(token, true)?: false
            }?.let { payload ->
                callback(payload.toEnroll)
            }?: callback(null)
        }, onError = {
            Logger.d(it)
            callback(null)
        })
    }

    private val Map<String, Any?>.toUser: User
        get() = User(
            get(kEmail) as String?,
            get(kMobile) as String?,
            get(kPassword) as String?,
            get(kFirstName) as String?,
            get(kLastName) as String?
        )

    private val Map<String, Any?>.toEnroll: Enroll
        get() = Enroll(
            get(kEmail) as String?,
            get(kMobile) as String?,
            get(kToken) as String?
        )

    private val User.toMap: Map<String, Any?>
        get() = mapOf(
            Pair(kId, email),
            Pair(kEmail, email),
            Pair(kMobile, mobile),
            Pair(kPassword, password),
            Pair(kFirstName, firstName),
            Pair(kLastName, lastName),
            Pair(kTimestamp, "$currentMillis")
        )

    private val Enroll.toMap: Map<String, Any?>
        get() = mapOf(
            Pair(kId, email),
            Pair(kEmail, email),
            Pair(kMobile, mobile),
            Pair(kToken, token),
            Pair(kTimestamp, "$currentMillis")
        )
}