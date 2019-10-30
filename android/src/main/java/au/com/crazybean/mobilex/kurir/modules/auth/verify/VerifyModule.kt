package au.com.crazybean.mobilex.kurir.modules.auth.verify

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val verifyModule = module {
    val qualifier = qualifier<VerifyWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(VerifyWorker(get()))
    }

    // Adviser
    factory { (scene: VerifyScene?) ->
        val viewModel = get<ViewModel<VerifyWorker>>(qualifier)
        VerifyAdviser(scene, viewModel.worker)
    }
}