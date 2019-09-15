package au.com.crazybean.mobilex.kurir.modules.chat.impl

import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseFragment
import au.com.crazybean.mobilex.kurir.modules.chat.ChatDelegate
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ChatFragment : BaseFragment<ChatDelegate>() {

    override val delegate: ChatDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.sketch_recycler
}