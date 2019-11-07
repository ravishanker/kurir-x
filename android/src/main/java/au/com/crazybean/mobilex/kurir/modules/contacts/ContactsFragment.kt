package au.com.crazybean.mobilex.kurir.modules.contacts

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import au.com.crazybean.foundation.widgets.RecyclerUtils
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ContactsFragment : RecyclerFragment<ContactsActor, User>(), ContactsScene {

    override val actor: ContactsActor? by inject {
        parametersOf(this)
    }

    override val adapter: RecyclerUtils.Adapter<User>? by lazy {
        ContactsAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.contacts, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_add_contact -> {
                actor?.onAddClick()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRefresh() {
        super.onRefresh()
        actor?.onRefresh()
    }

    override fun onEntitySelect(entity: User, position: Int) {
        super.onEntitySelect(entity, position)
        actor?.onContactClick(entity)
    }

    override fun showContacts(users: List<User>) {
        adapter?.addEntities(users)
    }

    override fun showChat(user: User) {
        val arguments = Module.Chat.arguments.with(Extra.USER, user, User.serializer())
            .with(Extra.DESC, "DESCRIPTION")
        navigate(arguments)
    }

    override fun showExplore() {
        navigate(Module.Explore.arguments)
    }

    override fun showEmpty() {
    }
}