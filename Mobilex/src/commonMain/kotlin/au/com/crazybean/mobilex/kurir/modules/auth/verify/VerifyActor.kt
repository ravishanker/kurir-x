package au.com.crazybean.mobilex.kurir.modules.auth.verify

import au.com.crazybean.mobilex.foundation.saw.Actor
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.fetch

class VerifyActor(scene: VerifyScene?,
                  wrapper: VerifyWrapper) : Actor<VerifyScene, VerifyWrapper>(scene, wrapper) {
    private var user: User? = null

    override fun onLoad(params: Map<String, Any?>) {
        super.onLoad(params)
        user = params.fetch(Extra.USER, User.serializer())
    }

    fun onVerifyClick(passcode: String) {
        if (passcode == "123456") {
            scene?.showProfile(user)
        } else {
            scene?.showCodeError()
        }
    }
}