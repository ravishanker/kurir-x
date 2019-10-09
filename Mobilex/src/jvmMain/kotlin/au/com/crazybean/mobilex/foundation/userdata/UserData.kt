package au.com.crazybean.mobilex.foundation.userdata

import au.com.crazybean.mobilex.foundation.internal.UserDataInterface
import au.com.crazybean.mobilex.foundation.logger.Logger

actual open class UserData : UserDataInterface {
    init {
        Logger.e("Mobilex: Android app should override UserData implementation!")
    }

    /**
     * Save value
     */
    actual override fun setInt(value: Int, forKey: String) {}
    actual override fun setLong(value: Long, forKey: String) {}
    actual override fun setBool(value: Boolean, forKey: String) {}
    actual override fun setString(value: String, forKey: String) {}

    /**
     * Fetch value
     */
    actual override fun getInt(forKey: String, defaultValue: Int): Int = Int.MAX_VALUE
    actual override fun getLong(forKey: String, defaultValue: Long): Long = Long.MAX_VALUE
    actual override fun getBool(forKey: String, defaultValue: Boolean): Boolean = false
    actual override fun getString(forKey: String, defaultValue: String?): String? = null

    /**
     * Remove the key.
     */
    actual override fun delete(forKey: String) {}

    /**
     * Checking existence of a key.
     */
    actual override fun exists(key: String): Boolean = false

    // Release the storage
    actual override fun clear() {}
}