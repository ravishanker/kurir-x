package au.com.crazybean.mobilex.kurir.modules.login

import androidx.lifecycle.Observer
import au.com.crazybean.mobilex.kurir.data.model.ERR_NONE
import au.com.crazybean.sdk.mvvm.Delegate

class LoginDelegate(view: LoginView?,
                    viewModel: LoginViewModel) : Delegate<LoginView, LoginViewModel>(view, viewModel) {

    fun onLoginClick(name: String?, password: String?) {
        when {
            name.isNullOrBlank() -> view?.showNameError()
            password.isNullOrBlank() -> view?.showPasswordError()
            else -> viewModel.login(name, password)
                    .observe(this, Observer { auth ->
                        auth?.takeIf { it.result == ERR_NONE }?.let {
                            view?.showDashboard()
                        }?: view?.showError(auth)
                    })
        }
    }

    fun onSignupClick() {
        view?.showRegister()
    }
}