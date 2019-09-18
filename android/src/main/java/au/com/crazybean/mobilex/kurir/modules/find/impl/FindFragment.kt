package au.com.crazybean.mobilex.kurir.modules.find.impl

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.base.BaseFragment
import au.com.crazybean.mobilex.kurir.modules.find.FindDelegate
import au.com.crazybean.mobilex.kurir.modules.find.FindView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FindFragment : BaseFragment<FindDelegate>(), FindView {
    override val delegate: FindDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.sketch_recycler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tasks, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }
}