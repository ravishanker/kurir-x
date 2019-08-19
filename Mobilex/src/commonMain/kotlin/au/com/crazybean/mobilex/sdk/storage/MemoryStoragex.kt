package au.com.crazybean.mobilex.sdk.storage

class MemoryStoragex : Storagex {
    private val mEntries = HashMap<String, Any?>()

    override fun setInt(value: Int, forKey: String) {
        mEntries[forKey] = value
    }

    override fun setLong(value: Long, forKey: String) {
        mEntries[forKey] = value
    }

    override fun setBool(value: Boolean, forKey: String) {
        mEntries[forKey] = value
    }

    override fun setString(value: String, forKey: String) {
        mEntries[forKey] = value
    }

    override fun getInt(forKey: String, defaultValue: Int): Int {
        return (mEntries[forKey] as Int?) ?: defaultValue
    }

    override fun getLong(forKey: String, defaultValue: Long): Long {
        return (mEntries[forKey] as Long?) ?: defaultValue
    }

    override fun getBool(forKey: String, defaultValue: Boolean): Boolean {
        return (mEntries[forKey] as Boolean?) ?: defaultValue
    }

    override fun getString(forKey: String, defaultValue: String?): String? {
        return (mEntries[forKey] as String?) ?: defaultValue
    }

    override fun delete(forKey: String): Storagex {
        mEntries.remove(forKey)
        return this
    }

    override fun exists(key: String): Boolean {
        return mEntries.containsKey(key)
    }

    override fun clear() {
        mEntries.clear()
    }
}