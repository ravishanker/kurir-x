package au.com.crazybean.mobilex.kurir.modules.settings

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.settings")
}

val settingsModule = module {
    // Delegate
    factory { (scene: SettingsScene?) ->
        val viewModel = get<ViewModel<SettingsWorker>>(qualifier)
        SettingsAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(SettingsWorker())
    }
}