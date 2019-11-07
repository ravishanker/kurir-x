package au.com.crazybean.mobilex.kurir.modules.details

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    val qualifier = qualifier<DetailsWrapper>()

    // Wrapper via ViewModel
    viewModel(qualifier) {
        ViewModel(DetailsWrapper(get(), get()))
    }

    // Actor
    factory { (scene: DetailsScene?) ->
        val viewModel = get<ViewModel<DetailsWrapper>>(qualifier)
        DetailsActor(scene, viewModel.wrapper)
    }
}