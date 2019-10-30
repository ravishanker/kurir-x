package au.com.crazybean.mobilex.kurir.modules.chat

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val chatModule = module {
    val qualifier = qualifier<ChatWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(ChatWorker(get(), get()))
    }

    // Adviser
    factory { (scene: ChatScene?) ->
        val viewModel = get<ViewModel<ChatWorker>>(qualifier)
        ChatAdviser(scene, viewModel.worker)
    }
}