package au.com.crazybean.mobilex.kurir.modules.creation.receiver

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.creation.receiver")
}

val receiverModule = module {
    // Delegate
    factory { (scene: ReceiverScene?) ->
        val viewModel = get<ViewModel<ReceiverWorker>>(qualifier)
        ReceiverAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(ReceiverWorker(get(), get()))
    }
}