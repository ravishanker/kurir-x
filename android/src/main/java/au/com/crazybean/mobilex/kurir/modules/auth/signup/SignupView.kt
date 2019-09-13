package au.com.crazybean.mobilex.kurir.modules.auth.signup

import au.com.crazybean.mobilex.kurir.modules.base.View

interface SignupView : View {
    fun showSignup()
    fun showLogin()
    fun showVerify()
    fun showProfile()
    fun showCodeError()
    fun showExists()
    fun showError()
    fun showDashboard()
    fun showButton()
    fun hideButton()
}