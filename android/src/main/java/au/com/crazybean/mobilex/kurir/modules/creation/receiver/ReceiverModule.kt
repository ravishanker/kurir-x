package au.com.crazybean.mobilex.kurir.modules.creation.receiver

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val receiverModule = module {
    val qualifier = qualifier<ReceiverWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(ReceiverWorker(get(), get()))
    }

    // Adviser
    factory { (scene: ReceiverScene?) ->
        val viewModel = get<ViewModel<ReceiverWorker>>(qualifier)
        ReceiverAdviser(scene, viewModel.worker)
    }
}