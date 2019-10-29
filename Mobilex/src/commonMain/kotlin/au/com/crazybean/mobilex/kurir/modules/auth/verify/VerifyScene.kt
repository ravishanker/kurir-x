package au.com.crazybean.mobilex.kurir.modules.auth.verify

import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.Scene

interface VerifyScene : Scene {
    fun showCodeError()
    fun showProfile(user: User?)
    fun showSpinner()
    fun hideSpinner()
}