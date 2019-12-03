package au.com.crazybean.mobilex.foundation.saw.pulse

class LiveData<T>: PulseObserver {
    private var version =
        INVALID_VERSION
    private var wrapper: DataWrapper<T>? = null

    private val observers by lazy {
        mutableListOf<Observer<T>>()
    }

    fun observe(completion: (T) -> Unit) {
        addObserver(null, completion, true)
    }

    fun observe(owner: PulseOwner, completion: (T) -> Unit) {
        addObserver(owner, completion, false)
    }

    var value: T
        @Suppress("UNCHECKED_CAST")
        get() = wrapper?.data as T
        set(data) {
            ++version
            if (wrapper == null) {
                wrapper =
                    DataWrapper(
                        data
                    )
            } else {
                wrapper?.data = data
            }

            observers.forEach {
                dispatch(data, it)
            }
        }

    private fun addObserver(owner: PulseOwner?, completion: (T) -> Unit, forever: Boolean) {
        val previous = observers.firstOrNull {
            it.owner == owner && it.forever == forever
        }

        observers.takeIf { previous == null }?.let {
            val observer =
                Observer(
                    owner,
                    completion,
                    forever
                )
            it.add(observer)
            owner?.pulse?.addObserver(this)
            wrapper?.data?.let { data ->
                dispatch(data, observer)
            }
        }
    }

    private fun dispatch(data: T, to: Observer<T>?) {
        to?.takeIf { it.lastVersion < version }?.notify(data, version)
    }

    override fun onEventUpdate(event: Pulse.Event) {
        wrapper?.let {
            observers.forEach { observer ->
                dispatch(it.data, observer)
            }
        }
    }

    /**
     * Versioning
     */
    companion object {
        private const val INVALID_VERSION = -1
    }

    /**
     * DataWrapper
     */
    private class DataWrapper<T>(var data: T)

    /**
     * Observer
     */
    private class Observer<T>(internal val owner: PulseOwner?,
                              internal val block: (T) -> Unit,
                              internal val forever: Boolean = false) {

        internal var lastVersion =
            INVALID_VERSION

        fun notify(data: T, version: Int) {
            if (owner?.pulse?.shouldBeActive == true || forever) {
                this.lastVersion = version
                block(data)
            }
        }
    }
}