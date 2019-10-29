package au.com.crazybean.mobilex.kurir.modules.tasks

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.tasks")
}

val tasksModule = module {
    // Delegate
    factory { (scene: TasksScene?) ->
        val viewModel = get<ViewModel<TasksWorker>>(qualifier)
        TasksAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(TasksWorker(get(), get(), get()))
    }
}