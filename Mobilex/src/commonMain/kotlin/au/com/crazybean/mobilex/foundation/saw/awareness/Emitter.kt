package au.com.crazybean.mobilex.foundation.saw.awareness

private const val INVALID_VERSION = -1

class Emitter<T>: AwarenessObserver {
    private var version = INVALID_VERSION
    private var wrapper: Wrapper<T>? = null
    private val observers by lazy {
        mutableListOf<Observer<T>>()
    }

    fun observe(completion: (T) -> Unit) {
        addObserver(null, completion, true)
    }

    fun observe(owner: AwarenessOwner, completion: (T) -> Unit) {
        addObserver(owner, completion, false)
    }

    var value: T
        get() = wrapper?.data as T
        set(data) {
            ++version
            if (wrapper == null) {
                wrapper = Wrapper(data)
            } else {
                wrapper?.data = data
            }

            observers.forEach {
                dispatch(data, it)
            }
        }

    private fun addObserver(owner: AwarenessOwner?, completion: (T) -> Unit, forever: Boolean) {
        val previous = observers.firstOrNull {
            it.owner == owner && it.forever == forever
        }

        observers.takeIf { previous == null }?.let {
            val observer = Observer(owner, completion, forever)
            it.add(observer)
            wrapper?.data?.let { data ->
                dispatch(data, observer)
            }
        }
    }

    private fun dispatch(data: T, to: Observer<T>?) {
        to?.takeIf { it.lastVersion < version }?.notify(data, version)
    }

    override fun onEventUpdate(event: Awareness.Event) {
        wrapper?.let {
            observers.forEach { observer ->
                dispatch(it.data, observer)
            }
        }
    }

    /**
     * Wrapper
     */
    private class Wrapper<T>(var data: T)

    /**
     * Observer
     */
    private class Observer<T>(internal val owner: AwarenessOwner?,
                              internal val block: (T) -> Unit,
                              internal val forever: Boolean = false) {

        internal var lastVersion = INVALID_VERSION

        fun notify(data: T, version: Int) {
            if (owner?.awareness?.shouldBeActive == true || forever) {
                this.lastVersion = version
                block(data)
            }
        }
    }
}