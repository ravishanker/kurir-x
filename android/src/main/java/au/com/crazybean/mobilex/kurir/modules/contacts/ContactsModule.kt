package au.com.crazybean.mobilex.kurir.modules.contacts

import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

private val qualifier by lazy {
    StringQualifier("modules.contacts")
}

val contactsModule = module {
    // Delegate
    factory { (scene: ContactsScene?) ->
        val viewModel = get<ViewModel<ContactsWorker>>(qualifier)
        ContactsAdviser(scene, viewModel.worker)
    }

    // ViewModel
    viewModel(qualifier) {
        ViewModel(ContactsWorker(get(), get(), get()))
    }
}