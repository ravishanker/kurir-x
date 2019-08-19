package au.com.crazybean.mobilex.repository.auth

import au.com.crazybean.mobilex.repository.auth.network.AuthNetworkDataSource

class AuthRepository(private val dataSource: AuthNetworkDataSource = AuthNetworkDataSource()) {
    suspend fun login(name: String, password: String) = dataSource.login(name, password)

    suspend fun register(name: String, password: String, mobile: String) = dataSource.register(name, password, mobile)
}