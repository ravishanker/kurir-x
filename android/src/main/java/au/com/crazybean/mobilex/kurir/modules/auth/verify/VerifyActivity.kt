package au.com.crazybean.mobilex.kurir.modules.auth.verify

import android.view.View
import android.widget.EditText
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.BaseActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class VerifyActivity : BaseActivity<VerifyAdviser>(), VerifyScene {
    override val adviser: VerifyAdviser? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.sketch_verify

    private val editText by lazy {
        findViewById<EditText>(R.id.passcode_edit)
    }

    override fun onViewLoad() {
        super.onViewLoad()

        findViewById<View>(R.id.verify_button)?.setOnClickListener {
            adviser?.onVerifyClick(editText?.text.toString().trim())
        }
    }

    override fun showCodeError() {
        showError(R.string.error_wrong_code)
    }

    override fun showProfile(user: User?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSpinner() {
        showLoading()
    }

    override fun hideSpinner() {
        hideLoading()
    }
}