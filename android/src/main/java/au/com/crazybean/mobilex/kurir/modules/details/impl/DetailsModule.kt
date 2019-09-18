package au.com.crazybean.mobilex.kurir.modules.details.impl

import au.com.crazybean.mobilex.kurir.modules.details.DetailsDelegate
import au.com.crazybean.mobilex.kurir.modules.details.DetailsView
import au.com.crazybean.mobilex.kurir.modules.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {
    // ViewModel
    viewModel {
        DetailsViewModel(get(), get())
    }

    // Delegate
    factory { (view: DetailsView?) ->
        DetailsDelegate(view, get())
    }
}