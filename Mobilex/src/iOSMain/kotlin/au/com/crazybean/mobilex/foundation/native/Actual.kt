package au.com.crazybean.mobilex.foundation.native
import kotlin.system.getTimeMillis

@Suppress("MayBeConstant")
actual val device: String = "iOS"

actual val currentMillis: Long
    get() = getTimeMillis()

internal actual fun log(message: String) {
    print(message)
}

internal actual val Any.simpleName: String
    get() = ""
