package au.com.crazybean.mobilex.kurir.modules.contacts

import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.View

interface ContactsView : View {
    fun showContacts(users: List<User>)
    fun showChat(arguments: Arguments)
    fun showSearch(arguments: Arguments)
    fun showEmpty()
}