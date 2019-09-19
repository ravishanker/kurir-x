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
    override fun getUser(mobile: String?, email: String?, completion: (User?) -> Unit) {
        storage.readArray(TABLE_USERS, completion = { entities, _ ->
            entities?.firstOrNull {
                (it[kEmail] as String?)?.equals(email, true) == true || (it[kMobile] as String?)?.equals(mobile, true) == true
            }?.let {
                completion(it.toUser)
            }?: completion(null)
        })
    }

    override fun getUsers(emails: List<String>?, completion: (List<User>?) -> Unit) {
        storage.readArray(TABLE_USERS, completion = { entities, _ ->
            entities?.filter {
                emails.isNullOrEmpty() || (it[kEmail] as String?)?.let { email ->
                    emails.contains(email)
                }?: false
            }?.let { result ->
                completion(result.map { it.toUser })
            }?: completion(null)
        })
    }

    override fun addUser(user: User, completion: (User?) -> Unit) {
        storage.writeData("$TABLE_USERS/${user.email}", user.toMap) { _, throwable ->
            throwable?.let {
                completion(null)
            }?: completion(user)
        }
    }

    override fun addEnroll(enroll: Enroll, completion: (Enroll?) -> Unit) {
        storage.writeData("$TABLE_ENROLL/${enroll.email}", enroll.toMap) { success, _ ->
            completion(enroll.takeIf { success })
        }
    }

    override fun getEnroll(token: String, completion: (Enroll?) -> Unit) {
        storage.readData("$TABLE_ENROLL/$token") { payload, _ ->
            completion(payload?.toEnroll)
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