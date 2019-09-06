package au.com.crazybean.mobilex.kurir.impl

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import au.com.crazybean.mobilex.foundation.userdata.UserData

class PersistUserData(context: Context, fileName: String? = null) : UserData {
    private val preference = fileName?.takeIf { it.isNotBlank() }?.let {
        context.getSharedPreferences(it, Application.MODE_PRIVATE)
    } ?: PreferenceManager.getDefaultSharedPreferences(context)

    override fun setInt(value: Int, forKey: String) {
        preference.edit().putInt(forKey, value).apply()
    }

    override fun setLong(value: Long, forKey: String) {
        preference.edit().putLong(forKey, value).apply()
    }

    override fun setBool(value: Boolean, forKey: String) {
        preference.edit().putBoolean(forKey, value).apply()
    }

    override fun setString(value: String, forKey: String) {
        preference.edit().putString(forKey, value).apply()
    }

    override fun getInt(forKey: String, defaultValue: Int): Int {
        return preference.getInt(forKey, defaultValue)
    }

    override fun getLong(forKey: String, defaultValue: Long): Long {
        return preference.getLong(forKey, defaultValue)
    }

    override fun getBool(forKey: String, defaultValue: Boolean): Boolean {
        return preference.getBoolean(forKey, defaultValue)
    }

    override fun getString(forKey: String, defaultValue: String?): String? {
        return preference.getString(forKey, defaultValue)
    }

    override fun delete(forKey: String): UserData {
        forKey.takeIf { it.isNotBlank() }?.let {
            preference.edit().remove(it).apply()
        }
        return this
    }

    override fun exists(key: String): Boolean {
        return preference.contains(key)
    }

    override fun clear() {
        preference.edit().clear().apply()
    }
}