package au.com.crazybean.mobilex.kurir.modules.contacts

import au.com.crazybean.mobilex.foundation.saw.Actor
import au.com.crazybean.mobilex.kurir.data.model.User

class ContactsActor(scene: ContactsScene?,
                    wrapper: ContactsWrapper) : Actor<ContactsScene, ContactsWrapper>(scene, wrapper) {

    override fun onLoad(params: Map<String, Any?>) {
        super.onLoad(params)
        onRefresh()
    }

    fun onRefresh() {
        scene?.showSpinner()
        wrapper.contacts
            .observe(this) {
                scene?.hideSpinner()
                it?.takeIf { it.isNotEmpty() }?.let { contacts ->
                    scene?.showContacts(contacts)
                }?: scene?.showEmpty()
            }
    }

    fun onAddClick() {
        scene?.showExplore()
    }

    fun onContactClick(user: User) {
        scene?.showChat(user)
    }
}