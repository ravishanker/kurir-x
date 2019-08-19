package au.com.crazybean.mobilex.kurir.modules.base

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import au.com.crazybean.sdk.navigator.Arguments

private val Arguments.target: Class<out AppCompatActivity>?
    get() = null

fun Arguments.resolve(context: Context?): Intent {
    return attach(context, target)
}