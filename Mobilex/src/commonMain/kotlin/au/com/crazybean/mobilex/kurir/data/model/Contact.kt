package au.com.crazybean.mobilex.kurir.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Contact(val name: String,
                   val mobile: String,
                   val email: String? = null)