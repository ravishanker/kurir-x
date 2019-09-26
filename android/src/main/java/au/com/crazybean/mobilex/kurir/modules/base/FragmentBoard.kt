package au.com.crazybean.mobilex.kurir.modules.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.*
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class FragmentBoard private constructor(private val activity: FragmentActivity?,
                                        private val viewPagerId: Int,
                                        private val tabLayoutId: Int,
                                        private val asSlides: Boolean = false,
                                        private val creator: Creator? = null,
                                        private val observer: Observer? = null) : FragmentManager.OnBackStackChangedListener {
    private val fragmentManager: FragmentManager? by lazy {
        activity?.supportFragmentManager?.apply {
            addOnBackStackChangedListener(this@FragmentBoard)
        }
    }

    private val targets by lazy {
        mutableListOf<String>()
    }

    private var adapter: Adapter? = null

    fun addTargets(tags: List<String>, reset: Boolean = false) {
        if (reset) {
            targets.clear()
        }
        targets.addAll(tags)

        // Check the adapter
        if (adapter == null) {
            adapter = fragmentManager?.takeIf { asSlides }?.let {
                Adapter(creator, targets, it)
            }

            // Set up the adapter info.
            activity?.let {
                it.findViewById<ViewPager>(viewPagerId)?.let { viewPager ->
                    viewPager.adapter = adapter

                    // Tab Layout
                    it.takeIf {
                        tabLayoutId != 0
                    }?.findViewById<TabLayout>(tabLayoutId)?.apply {
                        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(this))
                        addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                            override fun onTabReselected(tab: TabLayout.Tab?) {
                            }

                            override fun onTabUnselected(tab: TabLayout.Tab?) {
                                tab?.let { unselected ->
                                    observer?.onUnselected(unselected, targets[unselected.position], targets.size)
                                }
                            }

                            override fun onTabSelected(tab: TabLayout.Tab?) {
                                tab?.let { selected ->
                                    viewPager.currentItem = selected.position
                                    observer?.onSelected(selected, targets[selected.position], targets.size, true)
                                }
                            }
                        })

                        // Activate the first by default.
                        getTabAt(0)?.let { tab ->
                            observer?.onSelected(tab, targets[tab.position], targets.size, false)
                        }
                    }
                }
            }
        }
    }

    fun addTarget(tag: String, reset: Boolean = false) {
        addTargets(listOf(tag), reset)
    }

    fun add(@IdRes containerViewId: Int, tag: String, addToBackStack: Boolean = true, params: Bundle? = null): Fragment? {
        return getFragment(tag, params)?.also {
            fragmentManager?.beginTransaction()
                ?.add(containerViewId, it, tag)
                ?.commit(tag, addToBackStack)
        }
    }

    fun replace(@IdRes containerViewId: Int, tag: String, addToBackStack: Boolean = true, params: Bundle? = null): Fragment? {
        return getFragment(tag, params)?.also { fragment ->
            fragmentManager?.beginTransaction()
                ?.replace(containerViewId, fragment, tag)
                ?.commit(tag, addToBackStack)
        }
    }

    // FragmentManager.OnBackStackChangedListener
    override fun onBackStackChanged() {
        if (fragmentManager?.backStackEntryCount?: 0 <= 0) {
            fragmentManager?.removeOnBackStackChangedListener(this)
            activity?.finish()
        }
    }

    private fun FragmentTransaction.commit(tag: String, addToBackStack: Boolean) {
        this.also { transaction ->
            transaction.takeIf { addToBackStack && it.isAddToBackStackAllowed }
                ?.addToBackStack(tag)
        }.commit()
    }

    private fun getFragment(tag: String, params: Bundle?): Fragment? {
        return creator?.takeIf {
            fragmentManager != null
        }?.initiate(tag)?.also {
            it.arguments = params
        }
    }

    /**
     * Delegate for creating Sketch instance.
     */
    interface Creator {
        /**
         * @param tag: Fragment unique ID.
         */
        fun initiate(tag: String): Fragment?
    }

    /**
     * Observer for Fragment display.
     */
    interface Observer {
        /**
         * Callback when a tab is clicked.
         * @param tag: Fragment unique Tag.
         */
        fun onSelected(tab: TabLayout.Tab, tag: String, size: Int, manually: Boolean = true)

        /**
         * Callback when a tab is unselected.
         * @param tag: Fragment unique Tag.
         */
        fun onUnselected(tab: TabLayout.Tab, tag: String, size: Int)
    }

    /**
     * Adapter
     */
    private class Adapter(private val creator: Creator?,
                          private val targets: List<String>,
                          fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return targets.takeIf { it.size > position }?.get(position)?.let { id ->
                creator?.initiate(id) ?: Fragment()
            }?: Fragment()
        }

        override fun getCount() = targets.size
    }

    /**
     * Builder
     */
    class Builder(private val activity: FragmentActivity?,
                  private val asSlides: Boolean = false) {
        private var viewPagerId: Int = 0
        private var tabLayoutId: Int = 0
        private var creator: Creator? = null
        private var observer: Observer? = null

        fun setViewPager(@IdRes viewId: Int): Builder {
            viewPagerId = viewId
            return this
        }

        fun setTabLayout(@IdRes viewId: Int): Builder {
            tabLayoutId = viewId
            return this
        }

        fun setCreator(creator: Creator?): Builder {
            this.creator = creator
            return this
        }

        fun setObserver(observer: Observer?): Builder {
            this.observer = observer
            return this
        }

        fun build(): FragmentBoard {
            return FragmentBoard(activity, viewPagerId, tabLayoutId, asSlides, creator, observer)
        }
    }
}