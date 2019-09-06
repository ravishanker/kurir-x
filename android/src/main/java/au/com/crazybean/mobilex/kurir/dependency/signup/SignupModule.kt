package au.com.crazybean.mobilex.kurir.dependency.signup

import au.com.crazybean.mobilex.kurir.modules.signup.SignupDelegate
import au.com.crazybean.mobilex.kurir.modules.signup.SignupViewModel
import au.com.crazybean.mobilex.kurir.modules.signup.SignupView
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signupModule = module {
    // Delegate
    factory { (view: SignupView?) ->
        SignupDelegate(view, get())
    }

    // ViewModel
    viewModel {
        SignupViewModel(get())
    }
}