package au.com.crazybean.mobilex.foundation.saw

import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.foundation.native.simpleName
import au.com.crazybean.mobilex.foundation.saw.pulse.Pulse
import au.com.crazybean.mobilex.foundation.saw.pulse.PulseObserver
import au.com.crazybean.mobilex.foundation.saw.pulse.PulseOwner


open class Actor<out SCENE: Scene, out WRAPPER: Wrapper>(protected val scene: SCENE?,
                                                         protected val wrapper: WRAPPER) : PulseObserver,
    PulseOwner {
    private var owner: PulseOwner? = null

    // From AwarenessOwner
    override val pulse: Pulse? by lazy {
        Pulse(this)
    }

    /**
     * Delegate the awareness provider to Actor instance.
     */
    fun perform(owner: PulseOwner?, params: Map<String, Any?>? = null) {
        this.owner = owner
        owner?.pulse?.addObserver(this)
        onLoad(params?: emptyMap())
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
    protected open fun onRelease() {
        Logger.d("$simpleName: onRelease")
        owner?.pulse?.removeObserver(this)
    }

    /**
     * onAppear: Android.Activity.onStart()
     *           iOS.ViewController.viewWillAppear()
     */
    protected open fun onAppear() {
        Logger.d("$simpleName: onAppear")
    }

    /**
     * onDismiss: Android.Activity.onStop()
     *            iOS.ViewController.viewWillDisappear()
     */
    protected open fun onDismiss() {
        Logger.d("$simpleName: onDismiss")
    }

    /**
     * onActivate: Android.Activity.onResume()
     *             iOS.ViewController.viewDidAppear()
     */
    protected open fun onActivate() {
        Logger.d("$simpleName: onActivate")
    }

    /**
     * onDeactivate: Android.Activity.onPause()
     *               iOS.ViewController.viewDidDisappear()
     */
    protected open fun onDeactivate() {
        Logger.d("$simpleName: onDeactivate")
    }

    // From StateObserver
    override fun onEventUpdate(event: Pulse.Event) {
        when (event) {
            Pulse.Event.OnAppear -> {
                pulse?.status = Pulse.Status.Appeared
                onAppear()
            }

            Pulse.Event.OnActivate -> {
                pulse?.status = Pulse.Status.Activated
                onActivate()
            }

            Pulse.Event.OnDeactivate -> {
                pulse?.status = Pulse.Status.Appeared
                onDeactivate()
            }

            Pulse.Event.OnDismiss -> {
                pulse?.status = Pulse.Status.Loaded
                onDismiss()
            }

            Pulse.Event.OnRelease -> {
                pulse?.status = Pulse.Status.Released
                onRelease()
            }
            else -> Unit
        }

        pulse?.handleEvent(event)
    }
}