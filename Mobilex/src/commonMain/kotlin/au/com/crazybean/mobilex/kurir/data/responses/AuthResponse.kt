package au.com.crazybean.mobilex.kurir.data.responses

import au.com.crazybean.mobilex.kurir.data.model.Auth
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(val auth: Auth)