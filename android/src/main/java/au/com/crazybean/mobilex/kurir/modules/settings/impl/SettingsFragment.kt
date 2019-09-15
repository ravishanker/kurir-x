package au.com.crazybean.mobilex.kurir.modules.settings.impl

import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseFragment
import au.com.crazybean.mobilex.kurir.modules.settings.SettingsDelegate
import au.com.crazybean.mobilex.kurir.modules.settings.SettingsView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SettingsFragment : BaseFragment<SettingsDelegate>(), SettingsView {

    override val delegate: SettingsDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.sketch_recycler
}