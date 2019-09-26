package au.com.crazybean.mobilex.kurir.modules.creation.receiver.impl

import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseFragment
import au.com.crazybean.mobilex.kurir.modules.creation.receiver.ReceiverDelegate
import au.com.crazybean.mobilex.kurir.modules.creation.receiver.ReceiverView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ReceiverFragment : BaseFragment<ReceiverDelegate>(), ReceiverView {

    override val delegate: ReceiverDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_receiver
}