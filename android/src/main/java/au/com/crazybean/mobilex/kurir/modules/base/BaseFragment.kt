package au.com.crazybean.mobilex.kurir.modules.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.foundation.mvvm.ViewModel

abstract class BaseFragment<out DELEGATE: Delegate<View, ViewModel>> : Fragment() {
    protected abstract val delegate: DELEGATE?
    protected abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): android.view.View? {
        return inflater.inflate(layoutRes, container, false)?.also {
            delegate?.authorise(this, savedInstanceState)
        }
    }

    fun dismiss() {}
}