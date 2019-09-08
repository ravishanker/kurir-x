package au.com.crazybean.mobilex.kurir.data.model

import kotlinx.serialization.Serializable

internal const val kEmail = "email"
internal const val kMobile = "mobile"
internal const val kPassword = "password"
internal const val kFirstName = "first_name"
internal const val kLastName = "last_name"
internal const val kDeviceToken = "device_token"

@Serializable
data class User(val email: String? = null,
                val mobile: String? = null,
                var password: String? = null,
                var firstName: String? = null,
                var lastName: String? = null,
                val userToken: String? = null) {

    internal companion object {
        fun fromMap(payload: Map<String, Any?>?): User {
            return User(
                payload?.get(kEmail) as String?,
                payload?.get(kMobile) as String?,
                payload?.get(kPassword) as String?,
                payload?.get(kFirstName) as String?,
                payload?.get(kLastName) as String?,
                payload?.get(kDeviceToken) as String?
            )
        }
    }

    val payload: Map<String, Any?>
        get() = mapOf(
            Pair(kEmail, email),
            Pair(kMobile, mobile),
            Pair(kPassword, password),
            Pair(kFirstName, firstName),
            Pair(kLastName, lastName),
            Pair(kDeviceToken, userToken)
        )
}