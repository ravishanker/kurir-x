package au.com.crazybean.mobilex.kurir.modules.dashboard

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {
    val qualifier = qualifier<DashboardWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(DashboardWorker())
    }

    // Adviser
    factory { (scene: DashboardScene?) ->
        val viewModel = get<ViewModel<DashboardWorker>>(qualifier)
        DashboardAdviser(scene, viewModel.worker)
    }
}