package au.com.crazybean.mobilex.foundation.userdata

import au.com.crazybean.mobilex.foundation.internal.UserDataInterface
import platform.Foundation.NSRegistrationDomain
import platform.Foundation.NSUserDefaults

actual open class UserData : UserDataInterface {
    private val userDefaults by lazy {
        NSUserDefaults.standardUserDefaults
    }

    /**
     * Save value
     */
    actual override fun setInt(value: Int, forKey: String) {
        userDefaults.setInteger(value.toLong(), forKey)
        userDefaults.synchronize()
    }

    actual override fun setLong(value: Long, forKey: String) {
        userDefaults.setInteger(value, forKey)
        userDefaults.synchronize()
    }

    actual override fun setBool(value: Boolean, forKey: String) {
        userDefaults.setBool(value, forKey)
        userDefaults.synchronize()
    }

    actual override fun setString(value: String, forKey: String) {
        userDefaults.setObject(value, forKey)
        userDefaults.synchronize()

    }

    /**
     * Fetch value
     */
    actual override fun getInt(forKey: String, defaultValue: Int) = if (exists(forKey)) userDefaults.integerForKey(forKey).toInt() else defaultValue
    actual override fun getLong(forKey: String, defaultValue: Long) = if (exists(forKey)) userDefaults.integerForKey(forKey) else defaultValue
    actual override fun getBool(forKey: String, defaultValue: Boolean) = if (exists(forKey)) userDefaults.boolForKey(forKey) else defaultValue
    actual override fun getString(forKey: String, defaultValue: String?) = (userDefaults.takeIf { exists(forKey) }?.objectForKey(forKey) as String?) ?: defaultValue

    /**
     * Remove the key.
     */
    actual override fun delete(forKey: String) {
        userDefaults.removeObjectForKey(forKey)
        userDefaults.synchronize()
    }

    /**
     * Checking existence of a key.
     */
    actual override fun exists(key: String): Boolean = userDefaults.objectForKey(key) != null

    // Release the storage
    actual override fun clear() {
        userDefaults.removePersistentDomainForName(NSRegistrationDomain)
        userDefaults.synchronize()
    }
}