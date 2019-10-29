package au.com.crazybean.mobilex.kurir.modules.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import au.com.crazybean.mobilex.foundation.saw.awareness.Awareness
import au.com.crazybean.mobilex.foundation.saw.awareness.AwarenessObserver

class LifecycleDispatcher(private val observer: AwarenessObserver?) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    internal fun onStartEvent() {
        observer?.onEventUpdate(Awareness.Event.OnAppear)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    internal fun onStopEvent() {
        observer?.onEventUpdate(Awareness.Event.OnDismiss)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    internal fun onResumeEvent() {
        observer?.onEventUpdate(Awareness.Event.OnActivate)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    internal fun onPauseEvent() {
        observer?.onEventUpdate(Awareness.Event.OnDeactivate)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    internal fun onRelease() {
        observer?.onEventUpdate(Awareness.Event.OnRelease)
    }
}