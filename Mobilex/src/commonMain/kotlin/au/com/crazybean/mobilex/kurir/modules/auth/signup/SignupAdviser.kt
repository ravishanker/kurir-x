package au.com.crazybean.mobilex.kurir.modules.auth.signup

import au.com.crazybean.mobilex.foundation.saw.Adviser
import au.com.crazybean.mobilex.kurir.data.model.ERR_EXISTS
import au.com.crazybean.mobilex.kurir.data.model.ERR_NONE

class SignupAdviser(scene: SignupScene?,
                    worker: SignupWorker) : Adviser<SignupScene, SignupWorker>(scene, worker) {

    fun onRegister(mobile: String?, email: String?) {
        when {
            mobile.isNullOrBlank() -> Unit // TODO: Show error
            email.isNullOrBlank() -> Unit // TODO: Show error
            else -> {
                scene?.showSpinner()
                worker.signup(mobile, email)
                    .observe(this) {
                        scene?.hideSpinner()
                        when (it?.result) {
                            ERR_NONE -> scene?.showVerify(worker.enroll)
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