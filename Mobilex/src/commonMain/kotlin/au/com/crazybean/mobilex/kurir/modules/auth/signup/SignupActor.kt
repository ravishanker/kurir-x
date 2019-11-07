package au.com.crazybean.mobilex.kurir.modules.auth.signup

import au.com.crazybean.mobilex.foundation.saw.Actor
import au.com.crazybean.mobilex.kurir.data.model.ERR_EXISTS
import au.com.crazybean.mobilex.kurir.data.model.ERR_NONE

class SignupActor(scene: SignupScene?,
                  wrapper: SignupWrapper) : Actor<SignupScene, SignupWrapper>(scene, wrapper) {

    fun onRegister(mobile: String?, email: String?) {
        when {
            mobile.isNullOrBlank() -> Unit // TODO: Show error
            email.isNullOrBlank() -> Unit // TODO: Show error
            else -> {
                scene?.showSpinner()
                wrapper.signup(mobile, email)
                    .observe(this) {
                        scene?.hideSpinner()
                        when (it?.result) {
                            ERR_NONE -> scene?.showVerify(wrapper.enroll)
                            ERR_EXISTS -> scene?.showExists()
                            else -> scene?.showError()
                        }
                    }
            }
        }
    }

    fun onLoginClick() {
        scene?.showLogin()
        scene?.dismiss()
    }
}