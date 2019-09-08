package au.com.crazybean.mobilex.kurir.modules.login

import au.com.crazybean.mobilex.kurir.modules.common.View

interface LoginView : View {
    fun showRegister()
    fun showNameError()
    fun showPasswordError()
    fun showDashboard()
    fun showNotFound()
}