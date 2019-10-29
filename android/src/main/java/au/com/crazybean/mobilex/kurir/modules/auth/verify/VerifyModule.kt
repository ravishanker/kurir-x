package au.com.crazybean.mobilex.kurir.modules.auth.verify

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.auth.verify")
}

val verifyModule = module {
    // Delegate
    factory { (scene: VerifyScene?) ->
        val viewModel = get<ViewModel<VerifyWorker>>(qualifier)
        VerifyAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(VerifyWorker(get()))
    }
}