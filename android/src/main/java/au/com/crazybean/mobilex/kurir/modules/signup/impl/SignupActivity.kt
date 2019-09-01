package au.com.crazybean.mobilex.kurir.modules.signup.impl

import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseActivity
import au.com.crazybean.mobilex.kurir.modules.base.Module
import au.com.crazybean.mobilex.kurir.modules.signup.SignupDelegate
import au.com.crazybean.mobilex.modules.signup.SignupView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SignupActivity : BaseActivity<SignupDelegate>(), SignupView {
    override val delegate: SignupDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.activity_signup

    override fun showSignup() {
    }

    override fun showLogin() {
        navigate(Module.Login)
    }

    override fun showVerify() {
    }

    override fun showDashboard() {
        navigate(Module.Dashboard)
    }
}
