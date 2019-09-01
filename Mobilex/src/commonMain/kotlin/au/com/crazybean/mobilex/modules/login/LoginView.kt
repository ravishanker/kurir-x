package au.com.crazybean.mobilex.modules.login

import au.com.crazybean.mobilex.data.model.Auth

interface LoginView {
    fun showError(auth: Auth?)
    fun showRegister()
    fun showNameError()
    fun showPasswordError()
    fun showDashboard()
}