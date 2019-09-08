package au.com.crazybean.mobilex.kurir.modules.dashboard.impl

import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseActivity
import au.com.crazybean.mobilex.kurir.modules.dashboard.DashboardDelegate
import au.com.crazybean.mobilex.kurir.modules.dashboard.DashboardView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DashboardActivity : BaseActivity<DashboardDelegate>(), DashboardView {

    override val delegate: DashboardDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.activity_dashboard
}
