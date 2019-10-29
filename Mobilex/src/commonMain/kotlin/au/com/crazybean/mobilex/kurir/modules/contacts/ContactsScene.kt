package au.com.crazybean.mobilex.kurir.modules.contacts

import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.Scene

interface ContactsScene : Scene {
    fun showContacts(users: List<User>)
    fun showChat(user: User)
    fun showExplore()
    fun showEmpty()
    fun showSpinner()
    fun hideSpinner()
}