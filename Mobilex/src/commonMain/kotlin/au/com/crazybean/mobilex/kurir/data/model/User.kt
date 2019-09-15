package au.com.crazybean.mobilex.kurir.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(val email: String? = null,
                val mobile: String? = null,
                var password: String? = null,
                var firstName: String? = null,
                var lastName: String? = null)