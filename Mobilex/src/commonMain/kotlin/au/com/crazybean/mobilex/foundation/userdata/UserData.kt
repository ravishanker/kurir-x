package au.com.crazybean.mobilex.foundation.userdata

import au.com.crazybean.mobilex.foundation.internal.Persist

expect open class UserData : Persist {
    /**
     * Save value
     */
    override fun setInt(value: Int, forKey: String)
    override fun setLong(value: Long, forKey: String)
    override fun setBool(value: Boolean, forKey: String)
    override fun setString(value: String, forKey: String)

    /**
     * Fetch value
     */
    override fun getInt(forKey: String, defaultValue: Int): Int
    override fun getLong(forKey: String, defaultValue: Long): Long
    override fun getBool(forKey: String, defaultValue: Boolean): Boolean
    override fun getString(forKey: String, defaultValue: String?): String?

    /**
     * Remove the key.
     */
    override fun delete(forKey: String)

    /**
     * Checking existence of a key.
     */
    override fun exists(key: String): Boolean

    // Release the storage
    override fun clear()
}