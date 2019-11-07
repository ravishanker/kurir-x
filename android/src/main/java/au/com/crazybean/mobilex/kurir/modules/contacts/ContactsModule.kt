package au.com.crazybean.mobilex.kurir.modules.contacts

import au.com.crazybean.mobilex.kurir.extension.qualifier
import au.com.crazybean.mobilex.kurir.modules.base.ViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactsModule = module {
    val qualifier = qualifier<ContactsWrapper>()

    // Wrapper via ViewModel
    viewModel(qualifier) {
        ViewModel(ContactsWrapper(get(), get(), get()))
    }

    // Actor
    factory { (scene: ContactsScene?) ->
        val viewModel = get<ViewModel<ContactsWrapper>>(qualifier)
        ContactsActor(scene, viewModel.wrapper)
    }
}