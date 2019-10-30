package au.com.crazybean.mobilex.kurir.modules.contacts

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactsModule = module {
    val qualifier = qualifier<ContactsWorker>()

    // Worker via ViewModel
    viewModel(qualifier) {
        ViewModel(ContactsWorker(get(), get(), get()))
    }

    // Adviser
    factory { (scene: ContactsScene?) ->
        val viewModel = get<ViewModel<ContactsWorker>>(qualifier)
        ContactsAdviser(scene, viewModel.worker)
    }
}