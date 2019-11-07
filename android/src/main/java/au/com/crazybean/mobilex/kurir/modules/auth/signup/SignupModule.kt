package au.com.crazybean.mobilex.kurir.modules.auth.signup

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signupModule = module {
    val qualifier = qualifier<SignupWrapper>()

    // Wrapper via ViewModel
    viewModel(qualifier) {
        ViewModel(SignupWrapper(get()))
    }

    // Actor
    factory { (scene: SignupScene?) ->
        val viewModel = get<ViewModel<SignupWrapper>>(qualifier)
        SignupActor(scene, viewModel.wrapper)
    }
}