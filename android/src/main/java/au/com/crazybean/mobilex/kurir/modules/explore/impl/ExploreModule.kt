package au.com.crazybean.mobilex.kurir.modules.explore.impl

import au.com.crazybean.mobilex.kurir.modules.explore.ExploreDelegate
import au.com.crazybean.mobilex.kurir.modules.explore.ExploreView
import au.com.crazybean.mobilex.kurir.modules.explore.ExploreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val exploreModule = module {
    // ViewModel
    viewModel {
        ExploreViewModel(get())
    }

    // Delegate
    factory { (view: ExploreView?) ->
        ExploreDelegate(view, get())
    }
}