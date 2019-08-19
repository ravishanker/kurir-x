package au.com.crazybean.mobilex.kurir.modules.auth

import android.os.Bundle
import androidx.lifecycle.Observer
import au.com.crazybean.mobilex.modules.auth.AuthView
import au.com.crazybean.sdk.mvvm.Delegate

class AuthDelegate(view: AuthView?,
                   viewModel: AuthViewModel) : Delegate<AuthView, AuthViewModel>(view, viewModel) {

    fun onLoadClick() {
        viewModel.login("Loren", "1234")
                .observe(this, Observer {
                    view?.showLogin(it)
                })
    }

    override fun onCreate(params: Bundle) {
        super.onCreate(params)
        view?.showLogin(null)
    }
}