package au.com.crazybean.mobilex.foundation.network

import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.DEFAULT_PORT

data class HttpConfig(val host: String = "",
                      val port: Int = DEFAULT_PORT,
                      val secure: Boolean = true,
                      val register: ((KotlinxSerializer) -> Unit)? = null)