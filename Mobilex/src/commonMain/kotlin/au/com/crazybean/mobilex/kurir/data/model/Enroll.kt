package au.com.crazybean.mobilex.kurir.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Enroll(val email: String? = null,
                  val mobile: String? = null,
                  val token: String? = null)