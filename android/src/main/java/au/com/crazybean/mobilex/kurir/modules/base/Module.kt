package au.com.crazybean.mobilex.kurir.modules.base

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.mobilex.kurir.modules.dashboard.impl.DashboardActivity
import au.com.crazybean.mobilex.kurir.modules.login.impl.LoginActivity
import au.com.crazybean.mobilex.kurir.modules.signup.impl.SignupActivity

enum class Module {
    Login,
    Signup,
    Dashboard
}

private val targets by lazy {
    hashMapOf(
        Pair(Module.Login.ordinal, LoginActivity::class.java),
        Pair(Module.Signup.ordinal, SignupActivity::class.java),
        Pair(Module.Dashboard.ordinal, DashboardActivity::class.java)
    )
}

private val Arguments.target: Class<out AppCompatActivity>?
    get() = targets[module] as Class<out AppCompatActivity>?

val Module.arguments: Arguments
    get() = Arguments(ordinal)

fun Arguments.resolve(context: Context?): Intent {
    return attach(context, target)
}
