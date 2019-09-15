package au.com.crazybean.mobilex.kurir.modules.track.impl

import au.com.crazybean.mobilex.kurir.modules.track.TrackDelegate
import au.com.crazybean.mobilex.kurir.modules.track.TrackView
import au.com.crazybean.mobilex.kurir.modules.track.TrackViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trackModule = module {
    // ViewModel
    viewModel {
        TrackViewModel()
    }

    // Delegate
    factory { (view: TrackView?) ->
        TrackDelegate(view, get())
    }
}