package au.com.crazybean.mobilex.sdk.network

import io.ktor.client.HttpClient
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.Json
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json.Companion.nonstrict
import io.ktor.http.URLProtocol
import kotlinx.serialization.UnstableDefault

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
                @UseExperimental(UnstableDefault::class)
                Json {
                    serializer = KotlinxSerializer(json = nonstrict).also {
                        config.register?.invoke(it)
                    }
                }

                // Logging
                Logging {
                    logger = Logger.DEFAULT
                    level = LogLevel.INFO
                }
            }
        }
    }
}