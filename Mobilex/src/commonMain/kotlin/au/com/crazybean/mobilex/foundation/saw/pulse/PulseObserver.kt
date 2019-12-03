package au.com.crazybean.mobilex.foundation.saw.pulse

interface PulseObserver {
    fun onEventUpdate(event: Pulse.Event)
}