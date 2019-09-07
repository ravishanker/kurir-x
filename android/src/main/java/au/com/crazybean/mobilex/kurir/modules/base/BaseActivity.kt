package au.com.crazybean.mobilex.kurir.modules.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.modules.common.View
import au.com.crazybean.sdk.mvvm.Delegate
import au.com.crazybean.sdk.mvvm.ViewModel
import au.com.crazybean.sdk.navigator.Arguments
import au.com.crazybean.sdk.navigator.Navigator
import au.com.crazybean.sdk.widgets.MelonDialog

abstract class BaseActivity<out DELEGATE: Delegate<View, ViewModel>> : AppCompatActivity(), Navigator {
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

    fun dismiss() {
        finish()
    }

    protected fun showError(@StringRes resId: Int) {
        MelonDialog.Builder()
            .setTitle(R.string.error_title)
            .setMessage(resId)
            .setPositiveButton(R.string.button_gotcha)
            .build()
            .show(this)
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