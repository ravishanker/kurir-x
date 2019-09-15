package au.com.crazybean.mobilex.kurir.modules.auth.signup

import android.os.Bundle
import androidx.lifecycle.Observer
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.mobilex.kurir.data.model.ERR_EXISTS
import au.com.crazybean.mobilex.kurir.data.model.ERR_NONE

class SignupDelegate(view: SignupView?,
                     viewModel: SignupViewModel
) : Delegate<SignupView, SignupViewModel>(view, viewModel) {
    override fun onCreate(params: Bundle) {
        super.onCreate(params)
        view?.showSignup()
    }

    fun onSignupClick(mobile: String?, email: String?) {
        when {
            mobile.isNullOrBlank() -> Unit // TODO: Show error
            email.isNullOrBlank() -> Unit // TODO: Show error
            else -> {
                view?.showSpinner()
                viewModel.signup(mobile, email)
                    .observe(this, Observer {
                        view?.hideSpinner()
                        if (it?.result == ERR_NONE) {
                            view?.showVerify()
                        } else if (it?.result == ERR_EXISTS) {
                            view?.showExists()
                        } else {
                            view?.showError()
                        }
                    })
            }
        }
    }

    fun onLoginClick() {
        view?.showLogin()
        view?.dismiss()
    }

    fun onVerifyClick(passcode: String) {
        if (passcode == "123456") {
            view?.showProfile()
        } else {
            view?.showCodeError()
        }
    }

    fun onPasswordEditing(password: String?, confirm: String?) {
        if (password.isNullOrBlank() || confirm.isNullOrBlank()) {
            view?.hideButton()
        } else if (password == confirm) {
            view?.showButton()
        } else {
            view?.hideButton()
        }
    }

    fun onComplete(firstName: String?, lastName: String?, password: String?) {
        when {
            firstName.isNullOrBlank() -> Unit
            lastName.isNullOrBlank() -> Unit
            password.isNullOrBlank() -> Unit
            else -> {
                view?.showSpinner()
                viewModel.register(firstName, lastName, password)
                    .observe(this, Observer {
                        view?.hideSpinner()
                        // Handle the response.
                        view?.showDashboard()
                        view?.dismiss()
                    })
            }
        }
    }
}