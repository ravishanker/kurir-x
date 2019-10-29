package au.com.crazybean.mobilex.kurir.modules.auth.login

import au.com.crazybean.mobilex.foundation.saw.Adviser
import au.com.crazybean.mobilex.kurir.data.model.ERR_NONE
import au.com.crazybean.mobilex.kurir.data.model.ERR_NOT_FOUND
import au.com.crazybean.mobilex.kurir.data.model.ERR_PASSWORD

class LoginAdviser(scene: LoginScene?,
                   worker: LoginWorker) : Adviser<LoginScene, LoginWorker>(scene, worker) {
    fun onLoginClick(name: String?, password: String?) {
        when {
            name.isNullOrBlank() -> scene?.showNameError()
            password.isNullOrBlank() -> scene?.showPasswordError()
            else -> {
                scene?.showSpinner()
                worker.login(name, password)
                    .observe(this) { auth ->
                        scene?.hideSpinner()
                        when (auth?.result) {
                            ERR_NONE -> {
                                scene?.showDashboard()
                                scene?.dismiss()
                            }
                            ERR_NOT_FOUND -> scene?.showNotFound()
                            ERR_PASSWORD -> scene?.showPasswordError()
                        }
                    }
            }
        }
    }

    fun onSignupClick() {
        scene?.showRegister()
        scene?.dismiss()
    }
}