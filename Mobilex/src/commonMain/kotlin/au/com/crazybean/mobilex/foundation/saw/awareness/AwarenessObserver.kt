package au.com.crazybean.mobilex.foundation.saw.awareness

interface AwarenessObserver {
    fun onEventUpdate(event: Awareness.Event)
}