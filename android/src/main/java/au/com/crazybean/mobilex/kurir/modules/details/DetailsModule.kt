package au.com.crazybean.mobilex.kurir.modules.details

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    val qualifier = qualifier<DetailsWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(DetailsWorker(get(), get()))
    }

    // Adviser
    factory { (scene: DetailsScene?) ->
        val viewModel = get<ViewModel<DetailsWorker>>(qualifier)
        DetailsAdviser(scene, viewModel.worker)
    }
}