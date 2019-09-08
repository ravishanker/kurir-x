package au.com.crazybean.mobilex.kurir.repository.users.network

import au.com.crazybean.mobilex.foundation.extension.decode
import au.com.crazybean.mobilex.kurir.data.responses.AuthResponse
import au.com.crazybean.mobilex.kurir.repository.users.UsersSource
import au.com.crazybean.mobilex.foundation.network.HttpConfig
import au.com.crazybean.mobilex.foundation.network.HttpEngine
import au.com.crazybean.mobilex.kurir.data.model.User
import io.ktor.client.request.get

private val httpConfig = HttpConfig("localhost", 80, true) { serializer ->
    serializer.register(AuthResponse.serializer())
}

class UsersNetworkSource : HttpEngine(httpConfig),
    UsersSource {
    override suspend fun getUser(mobile: String?, email: String?): User? {
        return httpClient.get<String>("").decode(User.serializer())
    }

    override suspend fun addUser(user: User): User? {
        return httpClient.get<String>("").decode(User.serializer())
    }
}