package au.com.crazybean.mobilex.kurir.modules.auth.profile

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val profileModule = module {
    val qualifier = qualifier<ProfileWrapper>()

    // Wrapper via ViewModel
    viewModel(qualifier) {
        ViewModel(ProfileWrapper(get()))
    }

    // Actor
    factory { (scene: ProfileScene?) ->
        val viewModel = get<ViewModel<ProfileWrapper>>(qualifier)
        ProfileActor(scene, viewModel.wrapper)
    }
}