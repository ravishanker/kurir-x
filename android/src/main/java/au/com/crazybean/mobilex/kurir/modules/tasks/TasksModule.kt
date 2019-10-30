package au.com.crazybean.mobilex.kurir.modules.tasks

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tasksModule = module {
    val qualifier = qualifier<TasksWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(TasksWorker(get(), get(), get()))
    }

    // Adviser
    factory { (scene: TasksScene?) ->
        val viewModel = get<ViewModel<TasksWorker>>(qualifier)
        TasksAdviser(scene, viewModel.worker)
    }
}