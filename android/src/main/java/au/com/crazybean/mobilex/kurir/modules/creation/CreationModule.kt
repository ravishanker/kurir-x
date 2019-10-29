package au.com.crazybean.mobilex.kurir.modules.creation

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.creation")
}

val creationModule = module {
    // Delegate
    factory { (scene: CreationScene?) ->
        val viewModel = get<ViewModel<CreationWorker>>(qualifier)
        CreationAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(CreationWorker())
    }
}