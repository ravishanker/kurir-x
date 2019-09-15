package au.com.crazybean.mobilex.kurir.modules.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.foundation.mvvm.ViewModel
import au.com.crazybean.foundation.sketch.Sketch
import au.com.crazybean.mobilex.kurir.R

abstract class BaseSketch<out DELEGATE: Delegate<View, ViewModel>>(@LayoutRes layoutRes: Int,
                                                                   protected val delegate: DELEGATE?,
                                                                   private val authorising: Boolean = true) : Sketch(layoutRes) {
    init {
        setForwardTransition(R.anim.sketch_in_rightleft, R.anim.sketch_out_rightleft)
        setBackwardTransition(R.anim.sketch_in_leftright, R.anim.sketch_out_leftright)
    }

    override fun onRender(rootView: ViewGroup, bundle: Bundle?) {
        super.onRender(rootView, bundle)
        onViewLoad(rootView)
        delegate?.takeIf { authorising }?.authorise(this, bundle)
    }

    protected open fun onViewLoad(rootView: ViewGroup) {
    }
}