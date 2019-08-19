package au.com.crazybean.mobilex.modules.auth

import au.com.crazybean.mobilex.data.model.Auth
import au.com.crazybean.mobilex.repository.auth.AuthRepository
import au.com.crazybean.mobilex.sdk.wrapper.Wrapper

class AuthWrapper(private val repository: AuthRepository) : Wrapper() {
    fun login(name: String, password: String, completion: (Auth?) -> Unit) {
        execute({
            repository.login(name, password)
        }, completion)
    }
}