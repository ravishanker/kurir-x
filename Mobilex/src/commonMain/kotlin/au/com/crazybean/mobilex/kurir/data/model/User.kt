package au.com.crazybean.mobilex.kurir.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(val email: String?,
                val mobile: String?,
                val password: String?,
                val firstName: String?,
                val lastName: String?) {

    internal companion object {
        fun fromMap(payload: Map<String, Any?>?): User {
            return User(payload?.get("email") as String?,
                    payload?.get("mobile") as String?,
                    payload?.get("password") as String?,
                    payload?.get("first_name") as String?,
                    payload?.get("last_name") as String?)
        }
    }

    val payload: Map<String, Any?>
        get() = mapOf(Pair("email", email),
                Pair("mobile", mobile),
                Pair("password", password),
                Pair("first_name", firstName),
                Pair("last_name", lastName))
}