package au.com.crazybean.mobilex.foundation.saw.awareness

class Awareness(private val owner: AwarenessOwner?) {
    private val observers by lazy {
        mutableListOf<AwarenessObserver>()
    }

    fun addObserver(observer: AwarenessObserver) {
        observers.takeUnless { it.contains(observer) }?.add(observer)
    }

    fun removeObserver(observer: AwarenessObserver) {
        observers.remove(observer)
    }

    var status: Status = Status.Released
        internal set

    val shouldBeActive: Boolean
        get() = status.atLeast(Status.Appeared)

    fun handleEvent(event: Event) {
        owner?.awareness?.let { state ->
            state.status = Status.Activated
            observers.forEach { observer ->
                observer.onEventUpdate(event)
            }
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