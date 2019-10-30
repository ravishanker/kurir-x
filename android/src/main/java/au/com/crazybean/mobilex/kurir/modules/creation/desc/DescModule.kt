package au.com.crazybean.mobilex.kurir.modules.creation.desc

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val descModule = module {
    val qualifier = qualifier<DescWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(DescWorker(get()))
    }

    // Adviser
    factory { (scene: DescScene?) ->
        val viewModel = get<ViewModel<DescWorker>>(qualifier)
        DescAdviser(scene, viewModel.worker)
    }
}