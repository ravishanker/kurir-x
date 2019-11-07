package au.com.crazybean.mobilex.kurir.modules.auth.verify

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val verifyModule = module {
    val qualifier = qualifier<VerifyWrapper>()

    // Wrapper via ViewModel
    viewModel(qualifier) {
        ViewModel(VerifyWrapper(get()))
    }

    // Actor
    factory { (scene: VerifyScene?) ->
        val viewModel = get<ViewModel<VerifyWrapper>>(qualifier)
        VerifyActor(scene, viewModel.wrapper)
    }
}