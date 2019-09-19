package au.com.crazybean.mobilex.kurir.modules.contacts

import androidx.lifecycle.Observer
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.Module
import au.com.crazybean.mobilex.kurir.modules.base.arguments
import au.com.crazybean.mobilex.kurir.modules.base.with

class ContactsDelegate(view: ContactsView?,
                       viewModel: ContactsViewModel) : Delegate<ContactsView, ContactsViewModel>(view, viewModel) {

    override fun onResume() {
        super.onResume()

        viewModel.contacts
            .observe(this, Observer {
                it?.takeIf { it.isNotEmpty() }?.let { contacts ->
                    view?.showContacts(contacts)
                }?: view?.showEmpty()
            })
    }

    fun onAddClick() {
        view?.showExplore(Module.Explore.arguments)
    }

    fun onContactClick(user: User) {
        Module.Chat.arguments
            .with(Extra.USER, user, User.serializer()).let {
                view?.showChat(it)
            }
    }
}