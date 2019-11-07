package au.com.crazybean.mobilex.kurir.modules.tasks

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tasksModule = module {
    val qualifier = qualifier<TasksWrapper>()

    // Wrapper via ViewModel
    viewModel(qualifier) {
        ViewModel(TasksWrapper(get(), get(), get()))
    }

    // Actor
    factory { (scene: TasksScene?) ->
        val viewModel = get<ViewModel<TasksWrapper>>(qualifier)
        TasksActor(scene, viewModel.wrapper)
    }
}