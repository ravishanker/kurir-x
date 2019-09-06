package au.com.crazybean.mobilex.kurir.modules.login

import au.com.crazybean.mobilex.kurir.data.model.Auth

interface LoginView {
    fun showError(auth: Auth?)
    fun showRegister()
    fun showNameError()
    fun showPasswordError()
    fun showDashboard()
}