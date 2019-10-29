package au.com.crazybean.mobilex.kurir.modules.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.mobilex.kurir.modules.dashboard.DashboardActivity
import au.com.crazybean.mobilex.kurir.modules.auth.login.LoginActivity
import au.com.crazybean.mobilex.kurir.modules.auth.profile.ProfileActivity
import au.com.crazybean.mobilex.kurir.modules.auth.signup.SignupActivity
import au.com.crazybean.mobilex.kurir.modules.auth.verify.VerifyActivity
import au.com.crazybean.mobilex.kurir.modules.chat.ChatActivity
import au.com.crazybean.mobilex.kurir.modules.creation.CreationActivity
import au.com.crazybean.mobilex.kurir.modules.details.DetailsActivity
import au.com.crazybean.mobilex.kurir.modules.explore.ExploreActivity
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json

enum class Module {
    Login,
    Signup,
    Verify,
    Profile,
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
        Pair(Module.Verify.ordinal, VerifyActivity::class.java),
        Pair(Module.Profile.ordinal, ProfileActivity::class.java),
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
