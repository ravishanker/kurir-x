package au.com.crazybean.mobilex.kurir.modules.creation.desc

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.creation.desc")
}

val descModule = module {
    // Delegate
    factory { (scene: DescScene?) ->
        val viewModel = get<ViewModel<DescWorker>>(qualifier)
        DescAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(DescWorker(get()))
    }
}