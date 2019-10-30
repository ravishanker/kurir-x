package au.com.crazybean.mobilex.kurir.modules.settings

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    val qualifier = qualifier<SettingsWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(SettingsWorker())
    }

    // Adviser
    factory { (scene: SettingsScene?) ->
        val viewModel = get<ViewModel<SettingsWorker>>(qualifier)
        SettingsAdviser(scene, viewModel.worker)
    }
}