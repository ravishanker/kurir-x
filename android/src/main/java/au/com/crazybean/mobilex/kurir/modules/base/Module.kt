package au.com.crazybean.mobilex.kurir.modules.base

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import au.com.crazybean.mobilex.kurir.modules.login.impl.LoginActivity
import au.com.crazybean.mobilex.kurir.modules.signup.impl.SignupActivity
import au.com.crazybean.sdk.navigator.Arguments

enum class Module {
    Login,
    Signup,
    Dashboard;

    companion object {
        private val targets by lazy {
            hashMapOf(
                    Pair(Login.ordinal, LoginActivity::class.java),
                    Pair(Signup.ordinal, SignupActivity::class.java),
                    Pair(Dashboard.ordinal, LoginActivity::class.java)
            )
        }

        @JvmStatic
        fun getTarget(argument: Arguments): Class<out AppCompatActivity>? {
            return targets[argument.module]
        }
    }
}

val Module.arguments: Arguments
    get() = Arguments(ordinal)

fun Arguments.resolve(context: Context?): Intent {
    return attach(context, Module.getTarget(this))
}
