package au.com.crazybean.mobilex.kurir.modules.tasks.impl

import au.com.crazybean.mobilex.kurir.modules.tasks.TasksDelegate
import au.com.crazybean.mobilex.kurir.modules.tasks.TasksView
import au.com.crazybean.mobilex.kurir.modules.tasks.TasksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val tasksModule = module {
    // ViewModel
    viewModel {
        TasksViewModel(get(), get(), get())
    }

    // Delegate
    factory { (view: TasksView?) ->
        TasksDelegate(view, get())
    }
}