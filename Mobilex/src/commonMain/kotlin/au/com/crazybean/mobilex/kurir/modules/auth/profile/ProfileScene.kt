package au.com.crazybean.mobilex.kurir.modules.auth.profile

import au.com.crazybean.mobilex.kurir.modules.base.Scene

interface ProfileScene : Scene {
    fun showDashboard()
    fun showError()
    fun showNameError()
    fun showPasswordError()
    fun showButton()
    fun hideButton()
    fun showSpinner()
    fun hideSpinner()
}