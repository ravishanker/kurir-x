package au.com.crazybean.mobilex.kurir.modules.contacts

import android.os.Bundle
import androidx.lifecycle.Observer
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.Module
import au.com.crazybean.mobilex.kurir.modules.base.arguments
import au.com.crazybean.mobilex.kurir.modules.base.with

class ContactsDelegate(view: ContactsView?,
                       viewModel: ContactsViewModel) : Delegate<ContactsView, ContactsViewModel>(view, viewModel) {

    override fun onCreate(params: Bundle) {
        super.onCreate(params)
        onRefresh()
    }

    fun onRefresh() {
        view?.showSpinner()
        viewModel.contacts
            .observe(this, Observer {
                view?.hideSpinner()
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