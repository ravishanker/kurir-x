package au.com.crazybean.mobilex.sdk.storage

interface Storagex {
    /**
     * Save value
     */
    fun setInt(value: Int, forKey: String)
    fun setLong(value: Long, forKey: String)
    fun setBool(value: Boolean, forKey: String)
    fun setString(value: String, forKey: String)

    /**
     * Fetch value
     */
    fun getInt(forKey: String, defaultValue: Int = 0): Int
    fun getLong(forKey: String, defaultValue: Long = 0L): Long
    fun getBool(forKey: String, defaultValue: Boolean = false): Boolean
    fun getString(forKey: String, defaultValue: String? = null): String?

    /**
     * Remove the key.
     */
    fun delete(forKey: String): Storagex

    /**
     * Checking existence of a key.
     */
    fun exists(key: String): Boolean

    // Release the storage
    fun clear()
}