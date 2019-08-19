package au.com.crazybean.mobilex.sdk.wrapper

import au.com.crazybean.mobilex.sdk.coroutines.customMainScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

open class Wrapper(private val mainScope: CoroutineScope = customMainScope) : CoroutineScope by mainScope {
    protected open fun onRelease() {
        cancel()
    }

    protected fun <T> execute(block: suspend () -> T, completion: ((T?) -> Unit)? = null) {
        launch {
            try {
                val result = block()
                completion?.invoke(result)
            } catch (error: Exception) {
                completion?.invoke(null)
            }
        }
    }
}