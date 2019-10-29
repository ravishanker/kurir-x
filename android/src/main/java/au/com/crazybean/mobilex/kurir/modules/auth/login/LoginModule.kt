package au.com.crazybean.mobilex.kurir.modules.auth.login

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.auth.login")
}

val loginModule = module {
    // Delegate
    factory { (scene: LoginScene?) ->
        val viewModel = get<ViewModel<LoginWorker>>(qualifier)
        LoginAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(LoginWorker(get(), get()))
    }
}