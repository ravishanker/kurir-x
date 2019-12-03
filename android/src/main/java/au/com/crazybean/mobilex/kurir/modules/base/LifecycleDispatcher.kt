package au.com.crazybean.mobilex.kurir.modules.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import au.com.crazybean.mobilex.foundation.saw.pulse.Pulse
import au.com.crazybean.mobilex.foundation.saw.pulse.PulseObserver

class LifecycleDispatcher(private val observer: PulseObserver?) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    internal fun onStartEvent() {
        observer?.onEventUpdate(Pulse.Event.OnAppear)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    internal fun onStopEvent() {
        observer?.onEventUpdate(Pulse.Event.OnDismiss)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    internal fun onResumeEvent() {
        observer?.onEventUpdate(Pulse.Event.OnActivate)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    internal fun onPauseEvent() {
        observer?.onEventUpdate(Pulse.Event.OnDeactivate)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    internal fun onRelease() {
        observer?.onEventUpdate(Pulse.Event.OnRelease)
    }
}