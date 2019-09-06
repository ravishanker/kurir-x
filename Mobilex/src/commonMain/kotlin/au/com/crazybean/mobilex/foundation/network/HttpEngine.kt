package au.com.crazybean.mobilex.foundation.network

import au.com.crazybean.mobilex.foundation.extension.kJson
import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.Json
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import io.ktor.http.URLProtocol

open class HttpEngine(private val httpConfig: HttpConfig? = null) {
    protected val httpClient by lazy {
        (httpConfig?: HttpConfig()).let { config ->
            HttpClient {
                // Url config
                defaultRequest {
                    url {
                        host = config.host
                        port = config.port
                        protocol = if (config.secure) URLProtocol.HTTPS else URLProtocol.HTTP
                    }
                }

                // Json serializer
                Json {
                    serializer = KotlinxSerializer(json = kJson).also {
                        config.register?.invoke(it)
                    }
                }

                // Logging
                Logging {
                    logger = Logger.SIMPLE
                    level = LogLevel.NONE
                }
            }
        }
    }
}