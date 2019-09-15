package au.com.crazybean.mobilex.kurir.modules.auth.login.impl

import au.com.crazybean.mobilex.kurir.modules.auth.login.LoginDelegate
import au.com.crazybean.mobilex.kurir.modules.auth.login.LoginViewModel
import au.com.crazybean.mobilex.kurir.modules.auth.login.LoginView
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    // Delegate
    factory { (view: LoginView?) ->
        LoginDelegate(view, get())
    }

    // ViewModel
    viewModel {
        LoginViewModel(get())
    }
}