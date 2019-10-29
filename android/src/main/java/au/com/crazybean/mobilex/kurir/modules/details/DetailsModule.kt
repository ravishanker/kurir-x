package au.com.crazybean.mobilex.kurir.modules.details

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.details")
}

val detailsModule = module {
    // Delegate
    factory { (scene: DetailsScene?) ->
        val viewModel = get<ViewModel<DetailsWorker>>(qualifier)
        DetailsAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(DetailsWorker(get(), get()))
    }
}