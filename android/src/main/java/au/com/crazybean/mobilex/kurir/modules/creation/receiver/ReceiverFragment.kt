package au.com.crazybean.mobilex.kurir.modules.creation.receiver

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ReceiverFragment : BaseFragment<ReceiverActor>(), ReceiverScene {
    private val nameEditor by lazy {
        view?.findViewById<EditText>(R.id.user_name_edit)
    }

    private val mobileEditor by lazy {
        view?.findViewById<EditText>(R.id.mobile_edit)
    }

    private val emailEditor by lazy {
        view?.findViewById<EditText>(R.id.email_edit)
    }

    override val actor: ReceiverActor? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_receiver

    override fun onViewLoad(layout: ViewGroup) {
        super.onViewLoad(layout)

        layout.findViewById<View?>(R.id.place_button)?.setOnClickListener {
            actor?.onPlaceClick(nameEditor?.text?.toString(), mobileEditor?.text?.toString(), emailEditor?.text?.toString())
        }
    }

    override fun showNoName() {
    }

    override fun showNoContact() {
    }

    override fun showSpinner() {
        showLoading()
    }

    override fun hideSpinner() {
        hideLoading()
    }

    override fun dismiss() {
        activity?.finish()
    }
}