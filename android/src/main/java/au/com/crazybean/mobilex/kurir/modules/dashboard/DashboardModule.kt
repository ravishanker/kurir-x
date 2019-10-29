package au.com.crazybean.mobilex.kurir.modules.dashboard

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.dashboard")
}

val dashboardModule = module {
    // Delegate
    factory { (scene: DashboardScene?) ->
        val viewModel = get<ViewModel<DashboardWorker>>(qualifier)
        DashboardAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(DashboardWorker())
    }
}