package au.com.crazybean.mobilex.kurir.repository.auth.network

import au.com.crazybean.mobilex.kurir.data.model.Auth
import au.com.crazybean.mobilex.kurir.data.responses.AuthResponse
import au.com.crazybean.mobilex.kurir.repository.auth.AuthDataSource
import au.com.crazybean.mobilex.foundation.network.HttpConfig
import au.com.crazybean.mobilex.foundation.network.HttpEngine
import io.ktor.client.request.get

private val httpConfig = HttpConfig("localhost", 80, true) { serializer ->
    serializer.register(AuthResponse.serializer())
}

class AuthNetworkDataSource : HttpEngine(httpConfig), AuthDataSource {
    override suspend fun login(name: String, password: String): Auth {
        return httpClient.get<AuthResponse>("").auth
    }

    override suspend fun register(name: String, password: String, mobile: String): Auth {
        return httpClient.get<AuthResponse>("").auth
    }
}