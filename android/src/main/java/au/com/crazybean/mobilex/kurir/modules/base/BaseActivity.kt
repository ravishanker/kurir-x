package au.com.crazybean.mobilex.kurir.modules.base

import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.foundation.mvvm.ViewModel
import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.foundation.navigator.Navigator
import au.com.crazybean.foundation.widgets.MelonDialog
import au.com.crazybean.mobilex.kurir.R

private const val kError = "errorDialog"
private const val kLoading = "loadingDialog"

abstract class BaseActivity<out DELEGATE: Delegate<View, ViewModel>> : AppCompatActivity(), Navigator {
    protected abstract val delegate: DELEGATE?
    protected abstract val layoutRes: Int

    private val dialogs by lazy {
        mutableMapOf<String, MelonDialog?>()
    }

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
        hideDialog(kError)
        MelonDialog.Builder()
            .setTitle(R.string.error_title)
            .setMessage(resId)
            .setPositiveButton(R.string.button_gotcha)
            .setOnDismissListener(DialogInterface.OnDismissListener {
                dialogs.remove(kError)
            })
            .build()
            .show(this)
            ?.also {
                dialogs[kError] = it
            }
    }

    protected fun showLoading() {
        hideDialog(kLoading)
        MelonDialog.Builder(R.style.LoadingTheme, true)
            .setLayout(R.layout.layer_loading)
            .setOnDismissListener(DialogInterface.OnDismissListener {
                dialogs.remove(kLoading)
            })
            .build()
            .show(this)
            ?.also {
                dialogs[kLoading] = it
            }
    }

    protected fun hideLoading() {
        hideDialog(kLoading)
    }

    private fun hideDialog(key: String) {
        dialogs.remove(key)?.dismiss()
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