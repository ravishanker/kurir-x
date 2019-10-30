package au.com.crazybean.mobilex.kurir.modules.explore

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exploreModule = module {
    val qualifier = qualifier<ExploreWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(ExploreWorker(get()))
    }

    // Adviser
    factory { (scene: ExploreScene?) ->
        val viewModel = get<ViewModel<ExploreWorker>>(qualifier)
        ExploreAdviser(scene, viewModel.worker)
    }
}