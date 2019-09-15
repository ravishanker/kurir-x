package au.com.crazybean.mobilex.kurir.modules.settings.impl

import au.com.crazybean.mobilex.kurir.modules.settings.SettingsDelegate
import au.com.crazybean.mobilex.kurir.modules.settings.SettingsView
import au.com.crazybean.mobilex.kurir.modules.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val settingsModule = module {
    // ViewModel
    viewModel {
        SettingsViewModel()
    }

    // Delegate
    factory { (view: SettingsView?) ->
        SettingsDelegate(view, get())
    }
}