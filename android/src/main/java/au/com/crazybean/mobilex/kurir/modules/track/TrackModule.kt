package au.com.crazybean.mobilex.kurir.modules.track

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.track")
}

val trackModule = module {
    // Delegate
    factory { (scene: TrackScene?) ->
        val viewModel = get<ViewModel<TrackWorker>>(qualifier)
        TrackAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(TrackWorker())
    }
}