package au.com.crazybean.mobilex.foundation.userdata

import au.com.crazybean.mobilex.foundation.internal.Persist

class MemUserData : Persist {
    private val entities by lazy {
        mutableMapOf<String, Any?>()
    }

    override fun setInt(value: Int, forKey: String) {
        entities[forKey] = value
    }

    override fun setLong(value: Long, forKey: String) {
        entities[forKey] = value
    }

    override fun setBool(value: Boolean, forKey: String) {
        entities[forKey] = value
    }

    override fun setString(value: String, forKey: String) {
        entities[forKey] = value
    }

    override fun getInt(forKey: String, defaultValue: Int): Int {
        return (entities[forKey] as Int?) ?: defaultValue
    }

    override fun getLong(forKey: String, defaultValue: Long): Long {
        return (entities[forKey] as Long?) ?: defaultValue
    }

    override fun getBool(forKey: String, defaultValue: Boolean): Boolean {
        return (entities[forKey] as Boolean?) ?: defaultValue
    }

    override fun getString(forKey: String, defaultValue: String?): String? {
        return (entities[forKey] as String?) ?: defaultValue
    }

    override fun delete(forKey: String) {
        entities.remove(forKey)
    }

    override fun exists(key: String): Boolean {
        return entities.containsKey(key)
    }

    override fun clear() {
        entities.clear()
    }
}