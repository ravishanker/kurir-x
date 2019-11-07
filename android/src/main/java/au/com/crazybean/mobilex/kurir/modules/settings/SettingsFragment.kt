package au.com.crazybean.mobilex.kurir.modules.settings

import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SettingsFragment : BaseFragment<SettingsActor>(), SettingsScene {

    override val actor: SettingsActor? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.sketch_recycler
}