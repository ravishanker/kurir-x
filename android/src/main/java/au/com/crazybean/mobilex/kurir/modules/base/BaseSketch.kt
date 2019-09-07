package au.com.crazybean.mobilex.kurir.modules.base

import androidx.annotation.LayoutRes
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.common.View
import au.com.crazybean.sdk.mvvm.Delegate
import au.com.crazybean.sdk.mvvm.ViewModel
import au.com.crazybean.sdk.sketch.Sketch

open class BaseSketch<out DELEGATE: Delegate<View, ViewModel>>(@LayoutRes layoutRes: Int,
                                                               protected val delegate: DELEGATE?) : Sketch(layoutRes) {
    init {
        setForwardTransition(R.anim.sketch_in_rightleft, R.anim.sketch_out_rightleft)
        setBackwardTransition(R.anim.sketch_in_leftright, R.anim.sketch_out_leftright)
    }
}