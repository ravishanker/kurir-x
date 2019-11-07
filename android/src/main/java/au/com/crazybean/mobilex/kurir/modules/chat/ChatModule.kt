package au.com.crazybean.mobilex.kurir.modules.chat

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val chatModule = module {
    val qualifier = qualifier<ChatWrapper>()

    // Wrapper via ViewModel
    viewModel(qualifier) {
        ViewModel(ChatWrapper(get(), get()))
    }

    // Actor
    factory { (scene: ChatScene?) ->
        val viewModel = get<ViewModel<ChatWrapper>>(qualifier)
        ChatActor(scene, viewModel.wrapper)
    }
}