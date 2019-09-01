package au.com.crazybean.mobilex.sdk.native

@Suppress("MayBeConstant")
actual val device: String = "iOS"

internal actual fun log(message: String) {
    print(message)
}
