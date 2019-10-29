package au.com.crazybean.mobilex.kurir.modules.base

import au.com.crazybean.mobilex.foundation.extension.decode
import au.com.crazybean.mobilex.kurir.data.model.User
import kotlinx.serialization.DeserializationStrategy

val User.fullName: String
    get() = "$firstName $lastName"

fun <T: Any> Map<String, Any?>.fetch(key: String, serializer: DeserializationStrategy<T>): T? {
    return (get(key)?.takeIf { it is String } as String?)?.takeIf { it.isNotBlank() }?.decode(serializer)
}