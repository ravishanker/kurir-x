package au.com.crazybean.mobilex.kurir.modules.explore.impl

import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.foundation.widgets.RecyclerUtils
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.BaseActivity
import au.com.crazybean.mobilex.kurir.modules.contacts.impl.ContactsAdapter
import au.com.crazybean.mobilex.kurir.modules.explore.ExploreDelegate
import au.com.crazybean.mobilex.kurir.modules.explore.ExploreView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ExploreActivity : BaseActivity<ExploreDelegate>(), ExploreView, RecyclerUtils.Delegate<User>, SearchView.OnQueryTextListener {
    override val delegate: ExploreDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.activity_explore

    private val adapter by lazy {
        ContactsAdapter(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        delegate?.onQuery(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onEntitySelect(entity: User, position: Int) {
        super.onEntitySelect(entity, position)
        delegate?.onContactClick(entity)
    }

    override fun onViewLoad() {
        super.onViewLoad()
        findViewById<RecyclerView>(R.id.result_list)?.let {
            it.adapter = adapter
        }

        findViewById<SearchView>(R.id.search_view)?.setOnQueryTextListener(this)
    }

    override fun showResult(users: List<User>) {
        adapter.addEntities(users)
    }

    override fun showDetails(arguments: Arguments) {
        navigate(arguments)
    }

    override fun showEmpty() {
    }
}