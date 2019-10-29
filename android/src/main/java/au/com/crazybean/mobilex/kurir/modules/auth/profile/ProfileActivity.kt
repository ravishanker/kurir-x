package au.com.crazybean.mobilex.kurir.modules.auth.profile

import android.view.View
import android.widget.EditText
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseActivity
import au.com.crazybean.mobilex.kurir.modules.base.EditorWatcher
import au.com.crazybean.mobilex.kurir.modules.base.Module
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ProfileActivity : BaseActivity<ProfileAdviser>(), ProfileScene {
    override val adviser: ProfileAdviser? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.sketch_profile

    private val firstNameEdit by lazy {
        findViewById<EditText>(R.id.first_name_edit)
    }

    private val lastNameEdit by lazy {
        findViewById<EditText>(R.id.last_name_edit)
    }

    private val passwordEdit by lazy {
        findViewById<EditText>(R.id.password_edit)
    }

    private val confirmEdit by lazy {
        findViewById<EditText>(R.id.password_confirm_edit)
    }

    private val button by lazy {
        findViewById<View>(R.id.finish_button)
    }

    override fun onViewLoad() {
        super.onViewLoad()

        button?.setOnClickListener {
            val firstName = firstNameEdit?.text?.toString()?.trim()
            val lastName = lastNameEdit?.text?.toString()?.trim()
            val password = passwordEdit?.text?.toString()
            adviser?.onRegister(firstName, lastName, password)
        }

        passwordEdit?.addTextChangedListener(object: EditorWatcher {
            override fun onTextChanged(var1: CharSequence?, var2: Int, var3: Int, var4: Int) {
                onPasswordEditing()
            }
        })

        confirmEdit?.addTextChangedListener(object: EditorWatcher {
            override fun onTextChanged(var1: CharSequence?, var2: Int, var3: Int, var4: Int) {
                onPasswordEditing()
            }
        })

        button?.visibility = View.INVISIBLE
    }

    override fun showDashboard() {
        navigate(Module.Dashboard)
    }

    override fun showError() {
        showError(R.string.error_generic)
    }

    override fun showNameError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showPasswordError() {
        showError(R.string.error_wrong_password)
    }

    override fun showButton() {
        button?.visibility = View.VISIBLE
    }

    override fun hideButton() {
        button?.visibility = View.INVISIBLE
    }

    override fun showSpinner() {
        showLoading()
    }

    override fun hideSpinner() {
        hideLoading()
    }

    private fun onPasswordEditing() {
        val password = passwordEdit?.text?.toString()?.trim()
        val confirm = confirmEdit?.text?.toString()?.trim()
        adviser?.onPasswordType(password, confirm)
    }
}