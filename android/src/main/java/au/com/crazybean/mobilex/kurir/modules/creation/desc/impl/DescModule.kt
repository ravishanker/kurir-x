package au.com.crazybean.mobilex.kurir.modules.creation.desc.impl

import au.com.crazybean.mobilex.kurir.modules.creation.desc.DescDelegate
import au.com.crazybean.mobilex.kurir.modules.creation.desc.DescView
import au.com.crazybean.mobilex.kurir.modules.creation.desc.DescViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val descModule = module {
    // ViewModel
    viewModel {
        DescViewModel(get())
    }

    // Delegate
    factory { (view: DescView?) ->
        DescDelegate(view, get())
    }
}