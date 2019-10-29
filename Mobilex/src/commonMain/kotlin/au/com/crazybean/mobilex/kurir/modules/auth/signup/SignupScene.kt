package au.com.crazybean.mobilex.kurir.modules.auth.signup

import au.com.crazybean.mobilex.kurir.data.model.Enroll
import au.com.crazybean.mobilex.kurir.modules.base.Scene

interface SignupScene : Scene {
    fun showLogin()
    fun showVerify(enroll: Enroll?)
    fun showExists()
    fun showError()
    fun showSpinner()
    fun hideSpinner()
}