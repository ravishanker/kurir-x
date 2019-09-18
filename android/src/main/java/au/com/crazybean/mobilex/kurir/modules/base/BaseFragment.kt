package au.com.crazybean.mobilex.kurir.modules.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.foundation.mvvm.ViewModel
import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.foundation.navigator.Navigator

abstract class BaseFragment<out DELEGATE: Delegate<View, ViewModel>> : Fragment(), Navigator {
    protected abstract val delegate: DELEGATE?
    protected abstract val layoutRes: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): android.view.View? {
        return inflater.inflate(layoutRes, container, false)?.also { layout ->
            onViewLoad(layout as ViewGroup)
            delegate?.authorise(this, savedInstanceState)
        }
    }

    protected open fun onViewLoad(layout: ViewGroup) {

    }

    override fun navigate(arguments: Arguments, requestCode: Int) {
        arguments.resolve(context).let { intent ->
            when (requestCode != -1) {
                true -> startActivityForResult(intent, requestCode)
                else -> startActivity(intent)
            }
        }
    }

    protected fun navigate(module: Module, requestCode: Int = -1) {
        navigate(module.arguments, requestCode)
    }

    fun dismiss() {}
}