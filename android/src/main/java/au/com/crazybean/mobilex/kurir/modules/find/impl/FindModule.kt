package au.com.crazybean.mobilex.kurir.modules.find.impl

import au.com.crazybean.mobilex.kurir.modules.find.FindDelegate
import au.com.crazybean.mobilex.kurir.modules.find.FindView
import au.com.crazybean.mobilex.kurir.modules.find.FindViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val findModule = module {
    // ViewModel
    viewModel {
        FindViewModel()
    }

    // Delegate
    factory { (view: FindView?) ->
        FindDelegate(view, get())
    }
}