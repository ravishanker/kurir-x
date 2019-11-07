package au.com.crazybean.mobilex.kurir.modules.auth.signup

import android.view.View
import android.widget.EditText
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.data.model.Enroll
import au.com.crazybean.mobilex.kurir.modules.base.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SignupActivity : BaseActivity<SignupActor>(), SignupScene {
    override val actor: SignupActor? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.sketch_register

    private val mobEditText by lazy {
        findViewById<EditText>(R.id.mobile_edit)
    }

    private val emailEditText by lazy {
        findViewById<EditText>(R.id.email_edit)
    }

    override fun onViewLoad() {
        super.onViewLoad()
        findViewById<View>(R.id.login_button)?.setOnClickListener {
            actor?.onLoginClick()
        }

        findViewById<View>(R.id.signup_button)?.setOnClickListener {
            val mobile = mobEditText?.text?.toString()?.trim()
            val email = emailEditText?.text?.toString()?.trim()
            actor?.onRegister(mobile, email)
        }
    }

    override fun showLogin() {
        navigate(Module.Login)
    }

    override fun showVerify(enroll: Enroll?) {
        enroll?.let {
            navigate(Module.Dashboard.arguments.with(Extra.ENROLL, it, Enroll.serializer()))
        }
    }

    override fun showExists() {
        showError(R.string.error_user_exists)
    }

    override fun showError() {
        showError(R.string.error_generic)
    }

    override fun showSpinner() {
        showLoading()
    }

    override fun hideSpinner() {
        hideLoading()
    }
}
