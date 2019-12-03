package au.com.crazybean.mobilex.foundation.saw.pulse

class Pulse(private val owner: PulseOwner?) {
    private val observers by lazy {
        mutableListOf<PulseObserver>()
    }

    fun addObserver(observer: PulseObserver) {
        observers.takeUnless { it.contains(observer) }?.add(observer)
    }

    fun removeObserver(observer: PulseObserver) {
        observers.remove(observer)
    }

    var status: Status =
        Status.Released
        internal set

    val shouldBeActive: Boolean
        get() = status.atLeast(Status.Appeared)

    fun handleEvent(event: Event) {
        observers.filterNot { it == owner }.forEach { observer ->
            observer.onEventUpdate(event)
        }
    }

    /**
     * Event
     */
    enum class Event {
        OnLoad,
        OnAppear,
        OnActivate,
        OnDeactivate,
        OnDismiss,
        OnRelease
    }

    /**
     * Stand
     */
    enum class Status {
        Released,
        Loaded,
        Appeared,
        Activated;

        fun atLeast(stand: Status) = this.ordinal >= stand.ordinal
    }
}