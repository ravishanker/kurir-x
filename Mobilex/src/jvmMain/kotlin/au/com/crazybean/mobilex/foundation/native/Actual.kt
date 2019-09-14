package au.com.crazybean.mobilex.foundation.native

@Suppress("MayBeConstant")
actual val device: String = "Android"

actual val currentMillis: Long
    get() = System.currentTimeMillis()

internal actual fun log(message: String) {
    println(message)
}
