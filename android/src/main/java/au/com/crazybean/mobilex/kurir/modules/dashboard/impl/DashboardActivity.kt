package au.com.crazybean.mobilex.kurir.modules.dashboard.impl

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseActivity
import au.com.crazybean.mobilex.kurir.modules.chat.impl.ChatFragment
import au.com.crazybean.mobilex.kurir.modules.dashboard.DashboardDelegate
import au.com.crazybean.mobilex.kurir.modules.dashboard.DashboardView
import au.com.crazybean.mobilex.kurir.modules.find.impl.FindFragment
import au.com.crazybean.mobilex.kurir.modules.settings.impl.SettingsFragment
import au.com.crazybean.mobilex.kurir.modules.track.impl.TrackFragment
import com.google.android.material.tabs.TabLayout
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class DashboardActivity : BaseActivity<DashboardDelegate>(), DashboardView {

    private val tabIcons by lazy {
        arrayOf(Pair(R.drawable.ic_find, R.drawable.ic_find_on),
            Pair(R.drawable.ic_track, R.drawable.ic_track_on),
            Pair(R.drawable.ic_chat, R.drawable.ic_chat_on),
            Pair(R.drawable.ic_settings, R.drawable.ic_settings_on))
    }

    private var tabLayout: TabLayout? = null

    override val delegate: DashboardDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.activity_dashboard


    override fun onViewLoad() {
        super.onViewLoad()
        val viewPager = findViewById<ViewPager?>(R.id.view_pager)
        viewPager?.adapter = Adapter(supportFragmentManager)
        viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout = findViewById<TabLayout>(R.id.tab_layout)?.also {
            it.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.position?.let { pos ->
                        tab.setIcon(tabIcons[pos].first)
                    }
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.position?.let { pos ->
                        tab.setIcon(tabIcons[pos].second)
                        delegate?.onTabSelect(pos)
                    }
                }
            })

            // Set the first item focused
            it.getTabAt(0)?.setIcon(tabIcons[0].second)
        }
    }

    /**
     * PagerAdapter
     */
    private class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when(position) {
                0 -> FindFragment()
                1 -> TrackFragment()
                2 -> ChatFragment()
                else -> SettingsFragment()
            }
        }

        override fun getCount() = 4
    }
}
