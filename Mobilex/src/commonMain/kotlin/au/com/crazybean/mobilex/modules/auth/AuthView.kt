package au.com.crazybean.mobilex.modules.auth

import au.com.crazybean.mobilex.data.model.Auth

interface AuthView {
    fun showLogin(auth: Auth?)
    fun showRegister()
}