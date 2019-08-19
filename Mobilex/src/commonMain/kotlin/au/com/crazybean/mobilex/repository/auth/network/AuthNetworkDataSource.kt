package au.com.crazybean.mobilex.repository.auth.network

import au.com.crazybean.mobilex.data.model.Auth
import au.com.crazybean.mobilex.data.responses.AuthResponse
import au.com.crazybean.mobilex.repository.auth.AuthDataSource
import au.com.crazybean.mobilex.sdk.network.HttpConfig
import au.com.crazybean.mobilex.sdk.network.HttpEngine
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