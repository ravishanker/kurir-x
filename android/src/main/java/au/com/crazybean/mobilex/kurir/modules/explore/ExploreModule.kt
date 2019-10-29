package au.com.crazybean.mobilex.kurir.modules.explore

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.explore")
}

val exploreModule = module {
    // Delegate
    factory { (scene: ExploreScene?) ->
        val viewModel = get<ViewModel<ExploreWorker>>(qualifier)
        ExploreAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(ExploreWorker(get()))
    }
}