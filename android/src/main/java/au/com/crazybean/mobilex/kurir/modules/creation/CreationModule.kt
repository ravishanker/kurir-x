package au.com.crazybean.mobilex.kurir.modules.creation

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val creationModule = module {
    val qualifier = qualifier<CreationWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(CreationWorker())
    }

    // Adviser
    factory { (scene: CreationScene?) ->
        val viewModel = get<ViewModel<CreationWorker>>(qualifier)
        CreationAdviser(scene, viewModel.worker)
    }
}