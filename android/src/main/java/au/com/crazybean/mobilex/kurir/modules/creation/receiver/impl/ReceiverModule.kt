package au.com.crazybean.mobilex.kurir.modules.creation.receiver.impl

import au.com.crazybean.mobilex.kurir.modules.creation.receiver.ReceiverDelegate
import au.com.crazybean.mobilex.kurir.modules.creation.receiver.ReceiverView
import au.com.crazybean.mobilex.kurir.modules.creation.receiver.ReceiverViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val receiverModule = module {
    // ViewModel
    viewModel {
        ReceiverViewModel()
    }

    // Delegate
    factory { (view: ReceiverView?) ->
        ReceiverDelegate(view, get())
    }
}