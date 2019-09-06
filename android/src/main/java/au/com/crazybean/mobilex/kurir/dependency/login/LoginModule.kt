package au.com.crazybean.mobilex.kurir.dependency.login

import au.com.crazybean.mobilex.kurir.modules.login.LoginDelegate
import au.com.crazybean.mobilex.kurir.modules.login.LoginViewModel
import au.com.crazybean.mobilex.kurir.modules.login.LoginView
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