package au.com.crazybean.mobilex.kurir.modules.auth.impl

import au.com.crazybean.mobilex.data.model.Auth
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.auth.AuthDelegate
import au.com.crazybean.mobilex.kurir.modules.base.BaseActivity
import au.com.crazybean.mobilex.modules.auth.AuthView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class AuthActivity : BaseActivity<AuthDelegate>(), AuthView {
    override val delegate: AuthDelegate by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.activity_auth

    override fun showLogin(auth: Auth?) {
    }

    override fun showRegister() {

    }
}
