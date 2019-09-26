package au.com.crazybean.mobilex.kurir.modules.creation.impl

import au.com.crazybean.mobilex.kurir.modules.creation.CreationDelegate
import au.com.crazybean.mobilex.kurir.modules.creation.CreationView
import au.com.crazybean.mobilex.kurir.modules.creation.CreationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val creationModule = module {
    // ViewModel
    viewModel {
        CreationViewModel()
    }

    // Delegate
    factory { (view: CreationView?) ->
        CreationDelegate(view, get())
    }
}