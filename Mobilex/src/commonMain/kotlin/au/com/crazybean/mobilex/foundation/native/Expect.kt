package au.com.crazybean.mobilex.foundation.native

expect val device: String
expect val currentMillis: Long

internal expect fun log(message: String)

internal expect val Any.simpleName: String
