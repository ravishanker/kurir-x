package au.com.crazybean.mobilex.data.responses

import au.com.crazybean.mobilex.data.model.Auth
import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(val auth: Auth)