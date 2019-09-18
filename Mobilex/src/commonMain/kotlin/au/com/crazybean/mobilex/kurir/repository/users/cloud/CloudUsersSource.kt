package au.com.crazybean.mobilex.kurir.repository.users.cloud

import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.foundation.native.currentMillis
import au.com.crazybean.mobilex.kurir.data.model.Enroll
import au.com.crazybean.mobilex.kurir.storage.CloudStorage
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

class CloudUsersSource(private val storage: CloudStorage) : UsersSource {
    override fun getUser(mobile: String?, email: String?, callback: (User?) -> Unit) {
        storage.readArray(TABLE_USERS) { entities, _ ->
            entities?.firstOrNull {
                (it[kEmail] as String?)?.equals(email, true) == true || (it[kMobile] as String?)?.equals(mobile, true) == true
            }?.let {
                callback(it.toUser)
            }?: callback(null)
        }
    }

    override fun getUsers(emails: List<String>?, callback: (List<User>?) -> Unit) {
        storage.readArray(TABLE_USERS) { entities, _ ->
            entities?.filter {
                emails.isNullOrEmpty() || (it[kEmail] as String?)?.let { email ->
                    emails.contains(email)
                }?: false
            }?.let { result ->
                callback(result.map { it.toUser })
            }?: callback(null)
        }
    }

    override fun addUser(user: User, callback: (User?) -> Unit) {
        storage.writeData("$TABLE_USERS/${user.email}", user.toMap) { success, throwable ->
            throwable?.let {
                callback(null)
            }?: callback(user)
        }
    }

    override fun addEnroll(enroll: Enroll, callback: (Enroll?) -> Unit) {
        storage.writeData("$TABLE_ENROLL/${enroll.email}", enroll.toMap) { success, _ ->
            callback(enroll.takeIf { success })
        }
    }

    override fun getEnroll(token: String, callback: (Enroll?) -> Unit) {
        storage.readData("$TABLE_ENROLL/$token") { payload, throwable ->
            callback(payload?.toEnroll)
        }
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