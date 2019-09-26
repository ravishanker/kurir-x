package au.com.crazybean.mobilex.kurir.modules.creation.desc.impl

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseFragment
import au.com.crazybean.mobilex.kurir.modules.base.watcher
import au.com.crazybean.mobilex.kurir.modules.creation.desc.DescDelegate
import au.com.crazybean.mobilex.kurir.modules.creation.desc.DescView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DescFragment : BaseFragment<DescDelegate>(), DescView {
    override val delegate: DescDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_desc

    override fun onViewLoad(layout: ViewGroup) {
        super.onViewLoad(layout)
        layout.findViewById<View>(R.id.next_button)?.setOnClickListener {
            delegate?.onNextClick()
        }

        layout.findViewById<EditText>(R.id.desc_input_field)?.watcher = { _, desc ->
            delegate?.onDescUpdate(desc)
        }

        layout.findViewById<EditText>(R.id.auto_complete_edit)?.watcher = { _, query ->
            delegate?.onDestUpdate(query)
        }
    }

    override fun showNext() {
        view?.findViewById<View>(R.id.next_button)?.visibility = View.VISIBLE
    }

    override fun hideNext() {
        view?.findViewById<View>(R.id.next_button)?.visibility = View.INVISIBLE
    }

    override fun showReceiver(arguments: Arguments) {
        navigate(arguments)
    }

    override fun showWrongtDest() {
        showError(R.string.error_wrong_city)
    }
}