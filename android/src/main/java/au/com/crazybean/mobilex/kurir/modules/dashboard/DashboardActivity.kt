package au.com.crazybean.mobilex.kurir.modules.dashboard

import androidx.fragment.app.Fragment
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseActivity
import au.com.crazybean.mobilex.kurir.modules.base.FragmentBoard
import au.com.crazybean.mobilex.kurir.modules.contacts.ContactsFragment
import au.com.crazybean.mobilex.kurir.modules.tasks.TasksFragment
import au.com.crazybean.mobilex.kurir.modules.settings.SettingsFragment
import au.com.crazybean.mobilex.kurir.modules.track.TrackFragment
import com.google.android.material.tabs.TabLayout
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DashboardActivity : BaseActivity<DashboardActor>(), DashboardScene {

    private val tabIcons by lazy {
        arrayOf(Pair(R.drawable.ic_find, R.drawable.ic_find_on),
            Pair(R.drawable.ic_track, R.drawable.ic_track_on),
            Pair(R.drawable.ic_chat, R.drawable.ic_chat_on),
            Pair(R.drawable.ic_settings, R.drawable.ic_settings_on))
    }

    private val tags by lazy {
        arrayOf(R.string.tab_find, R.string.tab_track, R.string.tab_chat, R.string.tab_settings)
    }

    override val actor: DashboardActor? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.activity_dashboard


    override fun onViewLoad() {
        super.onViewLoad()

        FragmentBoard.Builder(this, true)
            .setViewPager(R.id.view_pager)
            .setTabLayout(R.id.tab_layout)
            .setCreator(object : FragmentBoard.Creator {
                override fun initiate(tag: String): Fragment? {
                    return when (tag) {
                        getString(R.string.tab_find) -> TasksFragment()
                        getString(R.string.tab_track) -> TrackFragment()
                        getString(R.string.tab_chat) -> ContactsFragment()
                        else -> SettingsFragment()
                    }
                }
            })
            .setObserver(object : FragmentBoard.Observer {
                override fun onSelected(tab: TabLayout.Tab, tag: String, size: Int, manually: Boolean) {
                    tab.setIcon(tabIcons[tab.position].second)
                    actor?.takeIf { manually }?.onTabSelect(tab.position)
                }

                override fun onUnselected(tab: TabLayout.Tab, tag: String, size: Int) {
                    tab.setIcon(tabIcons[tab.position].first)
                }
            })
            .build()
            .addTargets(tags.map { getString(it) })
    }
}
