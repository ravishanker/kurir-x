package au.com.crazybean.mobilex.kurir.modules.auth.profile

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val profileModule = module {
    val qualifier = qualifier<ProfileWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(ProfileWorker(get()))
    }

    // Adviser
    factory { (scene: ProfileScene?) ->
        val viewModel = get<ViewModel<ProfileWorker>>(qualifier)
        ProfileAdviser(scene, viewModel.worker)
    }
}