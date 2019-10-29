package au.com.crazybean.mobilex.foundation.logger

import au.com.crazybean.mobilex.foundation.native.log
import kotlin.Throwable

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
    private enum class Type {
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    // Info
    fun i(message: String, throwable: Throwable? = null) {
        output(Type.INFO, message, throwable)
    }

    fun i(throwable: Throwable?) {
        output(Type.INFO, null, throwable)
    }

    // Debug
    fun d(message: String, throwable: Throwable? = null) {
        output(Type.DEBUG, message, throwable)
    }

    fun d(throwable: Throwable?) {
        output(Type.DEBUG, null, throwable)
    }

    // Warn
    fun w(message: String, throwable: Throwable? = null) {
        output(Type.WARN, message, throwable)
    }

    fun w(throwable: Throwable?) {
        output(Type.WARN, null, throwable)
    }

    // Error
    fun e(message: String, throwable: Throwable? = null) {
        output(Type.ERROR, message, throwable)
    }

    fun e(throwable: Throwable?) {
        output(Type.ERROR, null, throwable)
    }

    private fun output(type: Type, message: String?, throwable: Throwable? = null) {
        val builder = StringBuilder()
            .append(type.name.first().toUpperCase())
            .append('/')

        message?.takeIf { it.isNotBlank() }?.let {
            builder.append(it)
                .append('\n')
        }

        throwable?.message?.let {
            builder.append(it)
                .append('\n')
        }

        log(builder.toString())
    }
}