package au.com.crazybean.mobilex.kurir.modules.creation

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val creationModule = module {
    val qualifier = qualifier<CreationWrapper>()

    // Wrapper via ViewModel
    viewModel(qualifier) {
        ViewModel(CreationWrapper())
    }

    // Actor
    factory { (scene: CreationScene?) ->
        val viewModel = get<ViewModel<CreationWrapper>>(qualifier)
        CreationActor(scene, viewModel.wrapper)
    }
}