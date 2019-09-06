package au.com.crazybean.mobilex.foundation.native

@Suppress("MayBeConstant")
actual val device: String = "Android"

internal actual fun log(message: String) {
    println(message)
}
