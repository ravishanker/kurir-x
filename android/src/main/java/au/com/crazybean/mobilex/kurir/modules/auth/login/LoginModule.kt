package au.com.crazybean.mobilex.kurir.modules.auth.login

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    val qualifier = qualifier<LoginWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(LoginWorker(get(), get()))
    }

    // Adviser
    factory { (scene: LoginScene?) ->
        val viewModel = get<ViewModel<LoginWorker>>(qualifier)
        LoginAdviser(scene, viewModel.worker)
    }
}