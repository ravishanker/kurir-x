package au.com.crazybean.mobilex.kurir.modules.chat.impl

import au.com.crazybean.mobilex.kurir.modules.chat.ChatDelegate
import au.com.crazybean.mobilex.kurir.modules.chat.ChatView
import au.com.crazybean.mobilex.kurir.modules.chat.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val chatModule = module {
    // ViewModel
    viewModel {
        ChatViewModel(get(), get())
    }

    // Delegate
    factory { (view: ChatView?) ->
        ChatDelegate(view, get())
    }
}