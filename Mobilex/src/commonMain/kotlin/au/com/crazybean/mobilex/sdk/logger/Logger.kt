package au.com.crazybean.mobilex.sdk.logger

import au.com.crazybean.mobilex.sdk.native.log
import kotlin.Throwable
import kotlin.jvm.JvmOverloads

// For iOS
fun i(message: String) {
    Logger.i(message)
}

fun i(throwable: Throwable?) {
    Logger.i(throwable)
}

// Debug
fun d(message: String) {
    Logger.d(message)
}

fun d(throwable: Throwable?) {
    Logger.d(throwable)
}

// Warn
fun w(message: String) {
    Logger.w(message)
}

fun w(throwable: Throwable?) {
    Logger.w(throwable)
}

// Error
fun e(message: String) {
    Logger.e(message)
}

fun e(throwable: Throwable?) {
    Logger.e(throwable)
}

// For Android
object Logger {
    // Info
    fun i(message: String, throwable: Throwable? = null) {
        output("INFO", message, throwable)
    }

    fun i(throwable: Throwable?) {
        output("INFO", null, throwable)
    }

    // Debug
    fun d(message: String, throwable: Throwable? = null) {
        output("DEBUG", message, throwable)
    }

    fun d(throwable: Throwable?) {
        output("DEBUG", null, throwable)
    }

    // Warn
    fun w(message: String, throwable: Throwable? = null) {
        output("WARN", message, throwable)
    }

    fun w(throwable: Throwable?) {
        output("WRAN", null, throwable)
    }

    // Error
    fun e(message: String, throwable: Throwable? = null) {
        output("ERROR", message, throwable)
    }

    fun e(throwable: Throwable?) {
        output("ERROR", null, throwable)
    }

    private fun output(label: String?, message: String?, throwable: Throwable? = null) {
        listOf(label, message, throwable?.message).filterNot {
            it.isNullOrEmpty()
        }.joinToString("-").takeIf {
            it.isNotEmpty()
        }?.let { output ->
            log(output)
        }
    }
}