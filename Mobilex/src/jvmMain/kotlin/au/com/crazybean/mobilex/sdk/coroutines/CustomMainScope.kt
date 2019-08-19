package au.com.crazybean.mobilex.sdk.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

actual val customMainScope: CoroutineScope = MainScope()
