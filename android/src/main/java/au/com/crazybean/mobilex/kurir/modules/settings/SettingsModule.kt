package au.com.crazybean.mobilex.kurir.modules.settings

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    val qualifier = qualifier<SettingsWrapper>()

    // Wrapper via ViewModel
    viewModel(qualifier) {
        ViewModel(SettingsWrapper())
    }

    // Actor
    factory { (scene: SettingsScene?) ->
        val viewModel = get<ViewModel<SettingsWrapper>>(qualifier)
        SettingsActor(scene, viewModel.wrapper)
    }
}