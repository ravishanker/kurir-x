package au.com.crazybean.mobilex.kurir.modules.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.foundation.navigator.Navigator
import au.com.crazybean.mobilex.foundation.saw.Wrapper
import au.com.crazybean.mobilex.foundation.saw.Actor
import au.com.crazybean.mobilex.foundation.saw.awareness.Awareness
import au.com.crazybean.mobilex.foundation.saw.awareness.AwarenessOwner
import au.com.crazybean.mobilex.kurir.extension.params

abstract class BaseFragment<out ACTOR: Actor<Scene, Wrapper>> : Fragment(), Navigator, AwarenessOwner {
    protected abstract val actor: ACTOR?
    protected abstract val layoutRes: Int

    private val dispatcher by lazy {
        LifecycleDispatcher(actor)
    }

    override val awareness: Awareness?
        get() = actor?.awareness

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): android.view.View? {
        return inflater.inflate(layoutRes, container, false)?.also { layout ->
            onViewLoad(layout as ViewGroup)
            actor?.perform(this, arguments?.params)
            lifecycle.addObserver(dispatcher)
        }
    }

    protected open fun onViewLoad(layout: ViewGroup) {

    }

    override fun navigate(arguments: Arguments, requestCode: Int) {
        arguments.resolve(context)?.let { intent ->
            when (requestCode != -1) {
                true -> startActivityForResult(intent, requestCode)
                else -> startActivity(intent)
            }
        }?: (activity?.takeIf { it is BaseActivity<*> } as BaseActivity<*>?)?.navigate(arguments, requestCode)
    }

    protected fun navigate(module: Module, requestCode: Int = -1) {
        navigate(module.arguments, requestCode)
    }

    protected fun showLoading() {
        (activity?.takeIf { it is BaseActivity<*> } as BaseActivity<*>?)?.showLoading()
    }

    protected fun hideLoading() {
        (activity?.takeIf { it is BaseActivity<*> } as BaseActivity<*>?)?.hideLoading()
    }

    protected fun showError(@StringRes resId: Int, onAction: (() -> Unit)? = null) {
        (activity?.takeIf { it is BaseActivity<*> } as BaseActivity<*>?)?.showError(resId, onAction)
    }

    open fun dismiss() {}
}