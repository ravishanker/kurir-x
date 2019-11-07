package au.com.crazybean.mobilex.kurir.modules.auth.login

import android.view.View
import android.widget.EditText
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseActivity
import au.com.crazybean.mobilex.kurir.modules.base.Module
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : BaseActivity<LoginActor>(), LoginScene {
    private var userEditor: EditText? = null
    private var passwordEditor: EditText? = null

    override val actor: LoginActor? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.activity_login

    override fun onViewLoad() {
        super.onViewLoad()
        userEditor = findViewById(R.id.user_name_edit)
        passwordEditor = findViewById(R.id.password_edit)

        findViewById<View?>(R.id.login_button)?.setOnClickListener {
            val name = userEditor?.text?.toString()
            val password= passwordEditor?.text?.toString()

            actor?.onLoginClick(name, password)
        }

        findViewById<View?>(R.id.signup_button)?.setOnClickListener {
            actor?.onSignupClick()
        }
    }

    override fun showDashboard() {
        navigate(Module.Dashboard)
    }

    override fun showNotFound() {
        showError(R.string.error_user_not_found)
    }

    override fun showRegister() {
        navigate(Module.Signup)
    }

    override fun showNameError() {
    }

    override fun showPasswordError() {
        showError(R.string.error_wrong_password)
    }

    override fun showSpinner() {
        showLoading()
    }

    override fun hideSpinner() {
        hideLoading()
    }
}
