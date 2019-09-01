package au.com.crazybean.mobilex.kurir.modules.login.impl

import android.view.View
import android.widget.EditText
import android.widget.Toast
import au.com.crazybean.mobilex.data.model.Auth
import au.com.crazybean.mobilex.data.model.ERR_NOT_FOUND
import au.com.crazybean.mobilex.data.model.ERR_PASSWORD
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.login.LoginDelegate
import au.com.crazybean.mobilex.kurir.modules.base.BaseActivity
import au.com.crazybean.mobilex.kurir.modules.base.Module
import au.com.crazybean.mobilex.modules.login.LoginView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : BaseActivity<LoginDelegate>(), LoginView {
    private var userEditor: EditText? = null
    private var passwordEditor: EditText? = null

    override val delegate: LoginDelegate? by inject {
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

            delegate?.onLoginClick(name, password)
        }

        findViewById<View?>(R.id.signup_button)?.setOnClickListener {
            delegate?.onSignupClick()
        }
    }

    override fun showDashboard() {
        navigate(Module.Dashboard)
    }

    override fun showError(auth: Auth?) {
        when (auth?.result) {
            ERR_NOT_FOUND -> Toast.makeText(this, R.string.error_user_not_found, Toast.LENGTH_SHORT).show()
            ERR_PASSWORD -> Toast.makeText(this, R.string.error_wrong_password, Toast.LENGTH_SHORT).show()
            else -> Unit
        }
    }

    override fun showRegister() {
        navigate(Module.Signup)
    }

    override fun showNameError() {
    }

    override fun showPasswordError() {
    }
}
