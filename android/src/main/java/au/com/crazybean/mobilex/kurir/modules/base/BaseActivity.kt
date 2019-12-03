package au.com.crazybean.mobilex.kurir.modules.base

import android.content.DialogInterface
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.foundation.navigator.Navigator
import au.com.crazybean.foundation.widgets.MelonDialog
import au.com.crazybean.mobilex.foundation.saw.Wrapper
import au.com.crazybean.mobilex.foundation.saw.Actor
import au.com.crazybean.mobilex.foundation.saw.pulse.Pulse
import au.com.crazybean.mobilex.foundation.saw.pulse.PulseOwner
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.extension.params

abstract class BaseActivity<out ACTOR: Actor<Scene, Wrapper>> : AppCompatActivity(), Navigator, PulseOwner {
    private val kError = "errorDialog"
    private val kLoading = "loadingDialog"
    protected abstract val actor: ACTOR?
    protected abstract val layoutRes: Int

    private val dialogs by lazy {
        mutableMapOf<String, MelonDialog?>()
    }

    private val dispatcher by lazy {
        LifecycleDispatcher(actor)
    }

    override val pulse: Pulse?
        get() = actor?.pulse

    protected open fun onViewLoad() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        onViewLoad()
        actor?.perform(this, intent.params)
        lifecycle.addObserver(dispatcher)
    }

    fun dismiss() {
        finish()
    }

    fun showError(@StringRes resId: Int, onAction: (() -> Unit)? = null) {
        hideDialog(kError)
        MelonDialog.Builder()
            .setTitle(R.string.error_title)
            .setMessage(resId)
            .setPositiveButton(R.string.button_gotcha, DialogInterface.OnClickListener { _, _ ->
                onAction?.invoke()
            })
            .setOnDismissListener(DialogInterface.OnDismissListener {
                dialogs.remove(kError)
            })
            .build()
            .show(this)
            ?.also {
                dialogs[kError] = it
            }
    }

    fun showLoading() {
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

    fun hideLoading() {
        hideDialog(kLoading)
    }

    private fun hideDialog(key: String) {
        dialogs.remove(key)?.dismiss()
    }

    override fun navigate(arguments: Arguments, requestCode: Int) {
        arguments.resolve(this)?.let { intent ->
            when (requestCode != -1) {
                true -> startActivityForResult(intent, requestCode)
                else -> startActivity(intent)
            }
        }?: process(arguments)
    }

    protected fun navigate(module: Module, requestCode: Int = -1) {
        navigate(module.arguments, requestCode)
    }

    protected open fun process(arguments: Arguments) {
    }
}