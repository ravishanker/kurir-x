package au.com.crazybean.mobilex.kurir.modules.auth.signup

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signupModule = module {
    val qualifier = qualifier<SignupWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(SignupWorker(get()))
    }

    // Adviser
    factory { (scene: SignupScene?) ->
        val viewModel = get<ViewModel<SignupWorker>>(qualifier)
        SignupAdviser(scene, viewModel.worker)
    }
}