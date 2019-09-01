package au.com.crazybean.mobilex.kurir.modules.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import au.com.crazybean.sdk.mvvm.Delegate
import au.com.crazybean.sdk.mvvm.ViewModel
import au.com.crazybean.sdk.navigator.Arguments
import au.com.crazybean.sdk.navigator.Navigator

abstract class BaseActivity<out DELEGATE: Delegate<Any, ViewModel>> : AppCompatActivity(), Navigator {
    protected abstract val delegate: DELEGATE?
    protected abstract val layoutRes: Int

    protected open fun onViewLoad() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        onViewLoad()
        delegate?.authorise(this, intent.extras)
    }

    override fun navigate(arguments: Arguments, requestCode: Int) {
        arguments.resolve(this).let { intent ->
            when (requestCode != -1) {
                true -> startActivityForResult(intent, requestCode)
                else -> startActivity(intent)
            }
        }
    }

    protected fun navigate(module: Module, requestCode: Int = -1) {
        navigate(module.arguments, requestCode)
    }
}