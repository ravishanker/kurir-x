package au.com.crazybean.mobilex.foundation.repository

import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.foundation.native.mainScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

open class Repository(private val coroutineScope: CoroutineScope = mainScope) : CoroutineScope by coroutineScope {
    fun onRelease() {
        cancel()
    }

    protected fun <T> execute(callback: ((T?) -> Unit)?, block: suspend () -> T) {
        launch {
            (try {
                block()
            } catch (error: Exception) {
                Logger.d(error)
                null
            }).let {
                callback?.invoke(it)
            }
        }
    }
}
