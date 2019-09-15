package au.com.crazybean.mobilex.kurir.modules.firebase

import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.kFirebaseToken
import com.google.firebase.messaging.FirebaseMessagingService
import org.koin.android.ext.android.inject

class CloudMessagingService : FirebaseMessagingService() {

    private val userData: UserData by inject()

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Logger.d("onNewToken: $token")
        token.takeIf { it.isNotBlank() }?.let {
            userData.setString(kFirebaseToken, it)
        }
    }
}