package au.com.crazybean.mobilex.kurir.modules.login

import androidx.lifecycle.Observer
import au.com.crazybean.mobilex.kurir.data.model.ERR_NONE
import au.com.crazybean.mobilex.kurir.data.model.ERR_NOT_FOUND
import au.com.crazybean.mobilex.kurir.data.model.ERR_PASSWORD
import au.com.crazybean.sdk.mvvm.Delegate

class LoginDelegate(view: LoginView?,
                    viewModel: LoginViewModel) : Delegate<LoginView, LoginViewModel>(view, viewModel) {

    fun onLoginClick(name: String?, password: String?) {
        when {
            name.isNullOrBlank() -> view?.showNameError()
            password.isNullOrBlank() -> view?.showPasswordError()
            else -> {
                viewModel.login(name, password)
                    .observe(this, Observer { auth ->
                        when (auth?.result) {
                            ERR_NONE -> {
                                view?.showDashboard()
                                view?.dismiss()
                            }
                            ERR_NOT_FOUND -> view?.showNotFound()
                            ERR_PASSWORD -> view?.showPasswordError()
                        }
                    })
            }
        }
    }

    fun onSignupClick() {
        view?.showRegister()
        view?.dismiss()
    }
}