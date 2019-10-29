package au.com.crazybean.mobilex.foundation.saw

import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.foundation.native.simpleName
import au.com.crazybean.mobilex.foundation.saw.awareness.Awareness
import au.com.crazybean.mobilex.foundation.saw.awareness.AwarenessObserver
import au.com.crazybean.mobilex.foundation.saw.awareness.AwarenessOwner

open class Adviser<out SCENE: Scene, out WORKER: Worker>(protected val scene: SCENE?,
                                                         protected val worker: WORKER) : AwarenessObserver, AwarenessOwner {
    private var owner: AwarenessOwner? = null

    private val current: Awareness by lazy {
        Awareness(this)
    }

    /**
     * Delegate the awareness provider to Wrapper instance.
     */
    fun consult(owner: AwarenessOwner?, params: Map<String, Any?>? = null) {
        owner?.let {
            this.owner = it
            it.awareness?.addObserver(this)
            onLoad(params?: emptyMap())
        }
    }

    /**
     * Lifecycle callback definition
     * onLoad: Android.Activity.onCreate()
     *         iOS.ViewController.viewDidLoad()
     */
    protected open fun onLoad(params: Map<String, Any?>) {
        Logger.d("$simpleName: onLoad")
    }

    /**
     * onRelease: Android.Activity.onDestroy()
     *            iOS.ViewController.deInit()
     */
    protected open  fun onRelease() {
        Logger.d("$simpleName: onRelease")
        owner?.awareness?.removeObserver(this)
    }

    /**
     * onAppear: Android.Activity.onStart()
     *           iOS.ViewController.viewWillAppear()
     */
    protected open  fun onAppear() {
        Logger.d("$simpleName: onAppear")
    }

    /**
     * onDismiss: Android.Activity.onStop()
     *            iOS.ViewController.viewWillDisappear()
     */
    protected open  fun onDismiss() {
        Logger.d("$simpleName: onDismiss")
    }

    /**
     * onActivate: Android.Activity.onResume()
     *             iOS.ViewController.viewDidAppear()
     */
    protected open  fun onActivate() {
        Logger.d("$simpleName: onActivate")
    }

    /**
     * onDeactivate: Android.Activity.onPause()
     *               iOS.ViewController.viewDidDisappear()
     */
    protected open  fun onDeactivate() {
        Logger.d("$simpleName: onDeactivate")
    }

    // From StateObserver
    override fun onEventUpdate(event: Awareness.Event) {
        when (event) {
            Awareness.Event.OnAppear -> {
                awareness?.status = Awareness.Status.Appeared
                onAppear()
            }

            Awareness.Event.OnActivate -> {
                awareness?.status = Awareness.Status.Activated
                onActivate()
            }

            Awareness.Event.OnDeactivate -> {
                awareness?.status = Awareness.Status.Appeared
                onDeactivate()
            }

            Awareness.Event.OnDismiss -> {
                awareness?.status = Awareness.Status.Loaded
                onDismiss()
            }

            Awareness.Event.OnRelease -> {
                awareness?.status = Awareness.Status.Released
                onRelease()
            }
            else -> Unit
        }
    }

    // From AwarenessOwner
    override val awareness: Awareness?
        get() = current
}