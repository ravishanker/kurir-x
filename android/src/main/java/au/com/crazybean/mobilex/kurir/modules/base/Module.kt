package au.com.crazybean.mobilex.kurir.modules.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.mobilex.foundation.extension.decode
import au.com.crazybean.mobilex.kurir.modules.dashboard.impl.DashboardActivity
import au.com.crazybean.mobilex.kurir.modules.auth.login.impl.LoginActivity
import au.com.crazybean.mobilex.kurir.modules.auth.signup.impl.SignupActivity
import au.com.crazybean.mobilex.kurir.modules.chat.impl.ChatActivity
import au.com.crazybean.mobilex.kurir.modules.creation.impl.CreationActivity
import au.com.crazybean.mobilex.kurir.modules.details.impl.DetailsActivity
import au.com.crazybean.mobilex.kurir.modules.explore.impl.ExploreActivity
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json

enum class Module {
    Login,
    Signup,
    Dashboard,
    Chat,
    Explore,
    Details,
    Creation,
    Desc,
    Receiver
}

private val targets by lazy {
    hashMapOf(
        Pair(Module.Login.ordinal, LoginActivity::class.java),
        Pair(Module.Signup.ordinal, SignupActivity::class.java),
        Pair(Module.Dashboard.ordinal, DashboardActivity::class.java),
        Pair(Module.Chat.ordinal, ChatActivity::class.java),
        Pair(Module.Explore.ordinal, ExploreActivity::class.java),
        Pair(Module.Details.ordinal, DetailsActivity::class.java),
        Pair(Module.Creation.ordinal, CreationActivity::class.java)
    )
}

private val Arguments.target: Class<out AppCompatActivity>?
    get() = targets[module] as Class<out AppCompatActivity>?

val Module.arguments: Arguments
    get() = Arguments(ordinal)

fun Arguments.resolve(context: Context?) = target?.let {
    attach(context, it)
}

fun <T: Any> Arguments.with(key: String, target: T, serializer: SerializationStrategy<T>) = with(key, Json.stringify(serializer, target))

fun <T: Any> Bundle.fetch(key: String, serializer: DeserializationStrategy<T>): T? = getString(key, "")?.takeIf { it.isNotEmpty() }?.decode(serializer)
