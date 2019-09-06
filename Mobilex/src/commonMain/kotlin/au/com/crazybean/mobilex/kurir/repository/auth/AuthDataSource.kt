package au.com.crazybean.mobilex.kurir.repository.auth

import au.com.crazybean.mobilex.kurir.data.model.Auth

interface AuthDataSource {
    suspend fun login(name: String, password: String): Auth
    suspend fun register(name: String, password: String, mobile: String): Auth
}