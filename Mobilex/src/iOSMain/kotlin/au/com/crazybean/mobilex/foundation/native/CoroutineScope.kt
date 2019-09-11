package au.com.crazybean.mobilex.foundation.native

import au.com.crazybean.mobilex.foundation.logger.Logger
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.Runnable
import platform.darwin.DISPATCH_TIME_NOW
import platform.darwin.dispatch_after
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_time
import kotlin.coroutines.CoroutineContext

internal actual val mainScope: CoroutineScope = MainScope()

private class MainScope : CoroutineScope {
    private val dispatcher = MainDispatcher()
    private val job = Job()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Logger.d("${throwable.message}: ${throwable.cause}")
    }

    override val coroutineContext: CoroutineContext
        get() = dispatcher + job + exceptionHandler
}

@UseExperimental(InternalCoroutinesApi::class)
private class MainDispatcher : CoroutineDispatcher(), Delay {
    @Suppress("TooGenericExceptionCaught")
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) {
            try {
                block.run()
            } catch (err: Throwable) {
                Logger.d(err)
                throw err
            }
        }
    }

    @InternalCoroutinesApi
    @Suppress("TooGenericExceptionCaught")
    override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, timeMillis * 1_000_000), dispatch_get_main_queue()) {
            try {
                with(continuation) {
                    resumeUndispatched(Unit)
                }
            } catch (err: Throwable) {
                Logger.d(err)
                throw err
            }
        }
    }

    @InternalCoroutinesApi
    @Suppress("TooGenericExceptionCaught")
    override fun invokeOnTimeout(timeMillis: Long, block: Runnable): DisposableHandle {
        val handle = object : DisposableHandle {
            var disposed = false
                private set

            override fun dispose() {
                disposed = true
            }
        }
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, timeMillis * 1_000_000), dispatch_get_main_queue()) {
            try {
                if (!handle.disposed) {
                    block.run()
                }
            } catch (err: Throwable) {
                Logger.d(err)
                throw err
            }
        }

        return handle
    }
}
