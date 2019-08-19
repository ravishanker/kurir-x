package au.com.crazybean.mobilex.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Auth(val result: Int,
                val error: String? = null)