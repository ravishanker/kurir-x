package au.com.crazybean.mobilex.foundation.extension

import au.com.crazybean.mobilex.foundation.logger.Logger
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

/**
 * Extension for Convert String to Deserialization
 */
internal val kJson by lazy {
    @UseExperimental(kotlinx.serialization.UnstableDefault::class)
    Json(JsonConfiguration(strictMode = false, useArrayPolymorphism = true))
}

internal fun <T: Any> String.decode(deserializer: DeserializationStrategy<T>): T? {
    return try {
        kJson.parse(deserializer, this)
    } catch (throwable: Exception) {
        Logger.d(throwable)
        null
    }
}