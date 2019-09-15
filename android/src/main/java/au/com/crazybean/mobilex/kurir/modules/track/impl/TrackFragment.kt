package au.com.crazybean.mobilex.kurir.modules.track.impl

import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseFragment
import au.com.crazybean.mobilex.kurir.modules.track.TrackDelegate
import au.com.crazybean.mobilex.kurir.modules.track.TrackView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class TrackFragment : BaseFragment<TrackDelegate>(), TrackView {
    override val delegate: TrackDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.sketch_recycler
}