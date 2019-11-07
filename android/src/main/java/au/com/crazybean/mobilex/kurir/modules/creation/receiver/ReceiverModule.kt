package au.com.crazybean.mobilex.kurir.modules.creation.receiver

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val receiverModule = module {
    val qualifier = qualifier<ReceiverWrapper>()

    // Wrapper via ViewModel
    viewModel(qualifier) {
        ViewModel(ReceiverWrapper(get(), get()))
    }

    // Actor
    factory { (scene: ReceiverScene?) ->
        val viewModel = get<ViewModel<ReceiverWrapper>>(qualifier)
        ReceiverActor(scene, viewModel.wrapper)
    }
}