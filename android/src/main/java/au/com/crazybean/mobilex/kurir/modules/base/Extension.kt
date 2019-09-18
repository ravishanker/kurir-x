package au.com.crazybean.mobilex.kurir.modules.base

import android.text.format.DateUtils
import au.com.crazybean.mobilex.kurir.data.model.User

val User.fullName: String
    get() = "$firstName $lastName"

val Long.relativeTime: String
    get() = DateUtils.getRelativeTimeSpanString(this).toString()
