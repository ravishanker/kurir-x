package au.com.crazybean.mobilex.kurir.modules.contacts.impl

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.foundation.widgets.RecyclerUtils
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.BaseFragment
import au.com.crazybean.mobilex.kurir.modules.contacts.ContactsDelegate
import au.com.crazybean.mobilex.kurir.modules.contacts.ContactsView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ContactsFragment : BaseFragment<ContactsDelegate>(), ContactsView, RecyclerUtils.Delegate<User> {

    override val delegate: ContactsDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.sketch_recycler

    private val adapter by lazy {
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
                delegate?.onAddClick()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewLoad(layout: ViewGroup) {
        super.onViewLoad(layout)
        layout.findViewById<RecyclerView>(R.id.recycler_view)?.let {
            it.adapter = adapter
        }
    }

    override fun onEntitySelect(entity: User, position: Int) {
        super.onEntitySelect(entity, position)
        delegate?.onContactClick(entity)
    }

    override fun showContacts(users: List<User>) {
        adapter.addEntities(users)
    }

    override fun showChat(arguments: Arguments) {
        navigate(arguments)
    }

    override fun showExplore(arguments: Arguments) {
        navigate(arguments)
    }

    override fun showEmpty() {
    }
}