package au.com.crazybean.mobilex.kurir.modules.track

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val trackModule = module {
    val qualifier = qualifier<TrackWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(TrackWorker())
    }

    // Adviser
    factory { (scene: TrackScene?) ->
        val viewModel = get<ViewModel<TrackWorker>>(qualifier)
        TrackAdviser(scene, viewModel.worker)
    }
}