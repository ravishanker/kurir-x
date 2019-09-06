package au.com.crazybean.mobilex.foundation.repository

import au.com.crazybean.mobilex.foundation.native.coroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

open class Repository(private val mainScope: CoroutineScope = coroutineScope) : CoroutineScope by mainScope {
    fun onRelease() {
        cancel()
    }

    protected fun <T> execute(callback: ((T?) -> Unit)?, block: suspend () -> T) {
        launch {
            try {
                val result = block()
                callback?.invoke(result)
            } catch (error: Exception) {
                callback?.invoke(null)
            }
        }
    }
}
