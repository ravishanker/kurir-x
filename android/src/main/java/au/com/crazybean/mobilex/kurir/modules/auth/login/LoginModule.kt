package au.com.crazybean.mobilex.kurir.modules.auth.login

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    val qualifier = qualifier<LoginWrapper>()

    // Wrapper via ViewModel
    viewModel(qualifier) {
        ViewModel(LoginWrapper(get(), get()))
    }

    // Actor
    factory { (scene: LoginScene?) ->
        val viewModel = get<ViewModel<LoginWrapper>>(qualifier)
        LoginActor(scene, viewModel.wrapper)
    }
}