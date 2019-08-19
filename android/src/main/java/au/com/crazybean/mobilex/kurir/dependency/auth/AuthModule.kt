package au.com.crazybean.mobilex.kurir.dependency.auth

import au.com.crazybean.mobilex.kurir.modules.auth.AuthDelegate
import au.com.crazybean.mobilex.kurir.modules.auth.AuthViewModel
import au.com.crazybean.mobilex.modules.auth.AuthView
import au.com.crazybean.mobilex.modules.auth.AuthWrapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    // AuthWrapper
    factory {
        AuthWrapper(get())
    }

    // Delegate
    factory { (view: AuthView?) ->
        AuthDelegate(view, get())
    }

    // ViewModel
    viewModel {
        AuthViewModel(get())
    }
}