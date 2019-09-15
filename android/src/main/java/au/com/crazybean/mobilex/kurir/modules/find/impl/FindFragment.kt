package au.com.crazybean.mobilex.kurir.modules.find.impl

import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseFragment
import au.com.crazybean.mobilex.kurir.modules.find.FindDelegate
import au.com.crazybean.mobilex.kurir.modules.find.FindView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FindFragment : BaseFragment<FindDelegate>(), FindView {
    override val delegate: FindDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.sketch_recycler
}