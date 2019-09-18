package au.com.crazybean.mobilex.kurir.modules.contacts.impl

import au.com.crazybean.mobilex.kurir.modules.contacts.ContactsDelegate
import au.com.crazybean.mobilex.kurir.modules.contacts.ContactsView
import au.com.crazybean.mobilex.kurir.modules.contacts.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactsModule = module {
    // ViewModel
    viewModel {
        ContactsViewModel(get(), get(), get())
    }

    // Delegate
    factory { (view: ContactsView?) ->
        ContactsDelegate(view, get())
    }
}