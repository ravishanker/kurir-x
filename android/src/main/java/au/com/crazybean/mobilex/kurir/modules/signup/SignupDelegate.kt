package au.com.crazybean.mobilex.kurir.modules.signup

import android.os.Bundle
import androidx.lifecycle.Observer
import au.com.crazybean.mobilex.kurir.data.model.ERR_EXISTS
import au.com.crazybean.mobilex.kurir.data.model.ERR_NOT_FOUND
import au.com.crazybean.sdk.mvvm.Delegate

class SignupDelegate(view: SignupView?,
                     viewModel: SignupViewModel) : Delegate<SignupView, SignupViewModel>(view, viewModel) {
    override fun onCreate(params: Bundle) {
        super.onCreate(params)
        view?.showSignup()
    }

    fun onSignupClick(mobile: String?, email: String?) {
        when {
            mobile.isNullOrBlank() -> Unit // TODO: Show error
            email.isNullOrBlank() -> Unit // TODO: Show error
            else -> viewModel.signup(mobile, email)
                .observe(this, Observer {
                    when (it?.result) {
                        ERR_NOT_FOUND -> view?.showVerify()
                        ERR_EXISTS -> view?.showExists()
                        else -> view?.showError()
                    }
                })
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

    fun onComplete(firstName: String?, lastName: String?, password: String?) {
        when {
            firstName.isNullOrBlank() -> Unit
            lastName.isNullOrBlank() -> Unit
            password.isNullOrBlank() -> Unit
            else -> viewModel.register(firstName, lastName, password)
                .observe(this, Observer {
                    // Handle the response.
                    view?.showDashboard()
                    view?.dismiss()
                })
        }
    }
}