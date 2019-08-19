package au.com.crazybean.mobilex.repository.auth

import au.com.crazybean.mobilex.data.model.Auth

interface AuthDataSource {
    suspend fun login(name: String, password: String): Auth
    suspend fun register(name: String, password: String, mobile: String): Auth
}