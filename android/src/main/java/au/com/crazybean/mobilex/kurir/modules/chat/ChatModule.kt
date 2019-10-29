package au.com.crazybean.mobilex.kurir.modules.chat

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.chat")
}

val chatModule = module {
    // Delegate
    factory { (scene: ChatScene?) ->
        val viewModel = get<ViewModel<ChatWorker>>(qualifier)
        ChatAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(ChatWorker(get(), get()))
    }
}