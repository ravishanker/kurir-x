package au.com.crazybean.mobilex.kurir.modules.auth.profile

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.auth.profile")
}

val profileModule = module {
    // Delegate
    factory { (scene: ProfileScene?) ->
        val viewModel = get<ViewModel<ProfileWorker>>(qualifier)
        ProfileAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(ProfileWorker(get()))
    }
}