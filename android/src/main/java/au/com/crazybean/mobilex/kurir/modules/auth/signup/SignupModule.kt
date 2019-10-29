package au.com.crazybean.mobilex.kurir.modules.auth.signup

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.auth.signup")
}

val signupModule = module {
    // Delegate
    factory { (scene: SignupScene?) ->
        val viewModel = get<ViewModel<SignupWorker>>(qualifier)
        SignupAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(SignupWorker(get()))
    }
}