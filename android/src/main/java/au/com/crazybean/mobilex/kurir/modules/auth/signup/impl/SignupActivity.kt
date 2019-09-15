package au.com.crazybean.mobilex.kurir.modules.auth.signup.impl

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import au.com.crazybean.foundation.sketch.Sketch
import au.com.crazybean.foundation.sketch.SketchBoard
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseActivity
import au.com.crazybean.mobilex.kurir.modules.base.BaseSketch
import au.com.crazybean.mobilex.kurir.modules.base.Module
import au.com.crazybean.mobilex.kurir.modules.auth.signup.SignupDelegate
import au.com.crazybean.mobilex.kurir.modules.auth.signup.SignupView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

private const val kSketchRegister = 0x100
private const val kSketchVerify = (kSketchRegister + 1)
private const val kSketchProfile = (kSketchRegister + 2)

class SignupActivity : BaseActivity<SignupDelegate>(), SignupView {
    private val sketchBoard by lazy {
        SketchBoard.Builder()
            .setPlaceholder(findViewById(R.id.sketch_placeholder))
            .setActivity(this)
            .setCreator(object : SketchBoard.Creator {
                override fun initiate(sketchId: Int): Sketch? {
                    return when (sketchId) {
                        kSketchRegister -> RegisterSketch(delegate)
                        kSketchVerify -> VerifySketch(delegate)
                        kSketchProfile -> ProfileSketch(delegate)
                        else -> null
                    }
                }
            })
            .build()
    }

    override val delegate: SignupDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.activity_signup

    override fun onViewLoad() {
        super.onViewLoad()
        findViewById<View>(R.id.login_button)?.setOnClickListener {
            delegate?.onLoginClick()
        }
    }

    override fun onBackPressed() {
        sketchBoard.handleBack().takeUnless { it }?.let {
            super.onBackPressed()
        }
    }

    override fun showSignup() {
        sketchBoard.showSketch(kSketchRegister)
    }

    override fun showLogin() {
        navigate(Module.Login)
    }

    override fun showVerify() {
        sketchBoard.showSketch(kSketchVerify)
    }

    override fun showProfile() {
        sketchBoard.showSketch(kSketchProfile)
    }

    override fun showCodeError() {
        showError(R.string.error_wrong_code)
    }

    override fun showExists() {
        showError(R.string.error_user_exists)
    }

    override fun showError() {
        showError(R.string.error_generic)
    }

    override fun showDashboard() {
        navigate(Module.Dashboard)
    }

    override fun showButton() {
        (sketchBoard.activeSketch as ProfileSketch?)?.showButton = true
    }

    override fun hideButton() {
        (sketchBoard.activeSketch as ProfileSketch?)?.showButton = false
    }

    override fun showSpinner() {
        showLoading()
    }

    override fun hideSpinner() {
        hideLoading()
    }
}

/**
 * Sketches
 */
private class RegisterSketch(delegate: SignupDelegate?) : BaseSketch<SignupDelegate>(R.layout.sketch_register, delegate, false) {
    private val mobEditText by lazy {
        layout?.findViewById<EditText>(R.id.mobile_edit)
    }

    private val emailEditText by lazy {
        layout?.findViewById<EditText>(R.id.email_edit)
    }

    override fun onRender(rootView: ViewGroup) {
        super.onRender(rootView)
        rootView.findViewById<View>(R.id.login_button)?.setOnClickListener {
            delegate?.onLoginClick()
        }

        rootView.findViewById<View>(R.id.signup_button)?.setOnClickListener {
            val mobile = mobEditText?.text?.toString()?.trim()
            val email = emailEditText?.text?.toString()?.trim()
            delegate?.onSignupClick(mobile, email)
        }
    }
}

private class VerifySketch(delegate: SignupDelegate?) : BaseSketch<SignupDelegate>(R.layout.sketch_verify, delegate, false) {
    private val editText by lazy {
        layout?.findViewById<EditText>(R.id.passcode_edit)
    }

    override fun onRender(rootView: ViewGroup) {
        super.onRender(rootView)
        rootView.findViewById<View>(R.id.verify_button)?.setOnClickListener {
            delegate?.onVerifyClick(editText?.text.toString().trim())
        }
    }
}

private class ProfileSketch(delegate: SignupDelegate?) : BaseSketch<SignupDelegate>(R.layout.sketch_profile, delegate, false) {
    private val firstNameEdit by lazy {
        layout?.findViewById<EditText>(R.id.first_name_edit)
    }

    private val lastNameEdit by lazy {
        layout?.findViewById<EditText>(R.id.last_name_edit)
    }

    private val passwordEdit by lazy {
        layout?.findViewById<EditText>(R.id.password_edit)
    }

    private val confirmEdit by lazy {
        layout?.findViewById<EditText>(R.id.password_confirm_edit)
    }

    private val button by lazy {
        layout?.findViewById<View>(R.id.finish_button)
    }

    override fun onRender(rootView: ViewGroup) {
        super.onRender(rootView)
        button?.setOnClickListener {
            val firstName = firstNameEdit?.text?.toString()?.trim()
            val lastName = lastNameEdit?.text?.toString()?.trim()
            val password = passwordEdit?.text?.toString()
            delegate?.onComplete(firstName, lastName, password)
        }

        passwordEdit?.addTextChangedListener(object: EditorWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                super.onTextChanged(p0, p1, p2, p3)
                onPasswordEditing()
            }
        })

        confirmEdit?.addTextChangedListener(object: EditorWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                super.onTextChanged(p0, p1, p2, p3)
                onPasswordEditing()
            }
        })

        showButton = false
    }

    var showButton: Boolean = false
        set(value) {
            button?.visibility = if (value) View.VISIBLE else View.INVISIBLE
        }

    private fun onPasswordEditing() {
        val password = passwordEdit?.text?.toString()?.trim()
        val confirm = confirmEdit?.text?.toString()?.trim()
        delegate?.onPasswordEditing(password, confirm)
    }

    private interface EditorWatcher : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    }
}
