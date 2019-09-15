package au.com.crazybean.mobilex.kurir.modules.dashboard.impl

import au.com.crazybean.mobilex.kurir.modules.dashboard.DashboardDelegate
import au.com.crazybean.mobilex.kurir.modules.dashboard.DashboardView
import au.com.crazybean.mobilex.kurir.modules.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {
    // ViewModel
    viewModel {
        DashboardViewModel()
    }

    // Delegate
    factory { (view: DashboardView?) ->
        DashboardDelegate(view, get())
    }
}