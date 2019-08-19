package au.com.crazybean.mobilex.sdk.navigator

class Arguments(val module: Int) {
    var flags: Int = 0
        private set

    private val extras by lazy {
        mutableMapOf<String, Any>()
    }

    fun with(name: String, value: Boolean): Arguments {
        extras[name] = value
        return this
    }

    fun with(name: String, value: Int): Arguments {
        extras[name] = value
        return this
    }

    fun with(name: String, value: Long): Arguments {
        extras[name] = value
        return this
    }

    fun with(name: String, value: Double): Arguments {
        extras[name] = value
        return this
    }

    fun with(name: String, value: String): Arguments {
        extras[name] = value
        return this
    }

    fun with(flags: Int): Arguments {
        this.flags = this.flags or flags
        return this
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        return (other.takeIf { it is Arguments } as Arguments?)?.let {
            (module == it.module) && (flags == it.flags)
        }?: false
    }

    override fun hashCode(): Int {
        return 31 * module + flags
    }
}