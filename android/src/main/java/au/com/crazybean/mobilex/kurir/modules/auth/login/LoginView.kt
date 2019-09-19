package au.com.crazybean.mobilex.kurir.modules.auth.login

import au.com.crazybean.mobilex.kurir.modules.base.View

interface LoginView : View {
    fun showRegister()
    fun showNameError()
    fun showPasswordError()
    fun showDashboard()
    fun showNotFound()
    fun showSpinner()
    fun hideSpinner()
}