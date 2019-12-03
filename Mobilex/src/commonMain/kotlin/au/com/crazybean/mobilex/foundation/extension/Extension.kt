package au.com.crazybean.mobilex.foundation.extension

import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.foundation.userdata.UserData
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

/**
 * Extension for Convert String to Deserialization
 */
internal val kJson by lazy {
    @UseExperimental(kotlinx.serialization.UnstableDefault::class)
    Json(JsonConfiguration(strictMode = false, useArrayPolymorphism = true))
}

fun <T: Any> String.decode(deserializer: DeserializationStrategy<T>): T? {
    return try {
        kJson.parse(deserializer, this)
    } catch (throwable: Exception) {
        Logger.d(throwable)
        null
    }
}

fun <T : Any> UserData.setObject(value: T, forKey: String, serializer: SerializationStrategy<T>) {
    setString(Json.stringify(serializer, value), forKey)
}

fun <T : Any> UserData.getObject(forKey: String, deserializer: DeserializationStrategy<T>, defaultValue: T? = null): T? {
    return getString(forKey, "")?.takeIf {
        it.isNotBlank()
    }?.decode(deserializer) ?: defaultValue
}
