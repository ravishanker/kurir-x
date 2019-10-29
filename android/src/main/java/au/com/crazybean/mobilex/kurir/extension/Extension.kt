package au.com.crazybean.mobilex.kurir.extension

import android.content.Intent
import android.os.Bundle

val Intent.params: Map<String, Any?>?
    get() = extras?.params

val Bundle.params: Map<String, Any?>?
    get() = keySet().map {
        it to get(it)
    }.toMap()