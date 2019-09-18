package au.com.crazybean.mobilex.kurir.modules.details.impl

import android.view.View
import android.widget.TextView
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.data.model.Task
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.BaseActivity
import au.com.crazybean.mobilex.kurir.modules.base.fullName
import au.com.crazybean.mobilex.kurir.modules.details.DetailsDelegate
import au.com.crazybean.mobilex.kurir.modules.details.DetailsView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DetailsActivity : BaseActivity<DetailsDelegate>(), DetailsView {
    private var nameLabel: TextView? = null
    private var emailLabel: TextView? = null
    private var mobileLabel: TextView? = null

    override val delegate: DetailsDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.activity_details

    override fun onViewLoad() {
        super.onViewLoad()
        nameLabel = findViewById(R.id.user_name_label)
        emailLabel = findViewById(R.id.email_label)
        mobileLabel = findViewById(R.id.mobile_label)

        findViewById<View>(R.id.action_button)?.setOnClickListener {
            delegate?.onButtonClick()
        }
    }

    override fun showUser(user: User) {
        nameLabel?.text = user.fullName
        emailLabel?.text = user.email
        mobileLabel?.text = user.mobile
    }

    override fun showTask(task: Task) {
    }

    override fun showError() {
        showError(R.string.error_generic) {
            dismiss()
        }
    }
}