package au.com.crazybean.mobilex.kurir.modules.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import au.com.crazybean.foundation.widgets.RecyclerUtils
import au.com.crazybean.mobilex.foundation.saw.Wrapper
import au.com.crazybean.mobilex.foundation.saw.Actor
import au.com.crazybean.mobilex.kurir.R

abstract class RecyclerFragment<out WRAPPER: Actor<Scene, Wrapper>, TYPE: Any> : BaseFragment<WRAPPER>(), RecyclerUtils.Delegate<TYPE> {
    abstract override val actor: WRAPPER?

    override val layoutRes: Int
        get() = R.layout.sketch_recycler

    private var refreshLayout: SwipeRefreshLayout? = null

    protected abstract val adapter: RecyclerUtils.Adapter<TYPE>?

    override fun onViewLoad(layout: ViewGroup) {
        super.onViewLoad(layout)
        refreshLayout = layout.findViewById<SwipeRefreshLayout?>(R.id.refresh_layout)
        refreshLayout?.setOnRefreshListener {
            onRefresh()
        }

        layout.findViewById<RecyclerView>(R.id.recycler_view)?.let {
            it.adapter = adapter
        }
    }

    protected open fun onRefresh() {
    }

    fun showSpinner() {
        refreshLayout?.isRefreshing = true
    }

    fun hideSpinner() {
        refreshLayout?.isRefreshing = false
    }
}