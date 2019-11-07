package au.com.crazybean.mobilex.kurir.modules.auth.profile

import au.com.crazybean.mobilex.foundation.saw.Wrapper
import au.com.crazybean.mobilex.foundation.saw.awareness.Emitter
import au.com.crazybean.mobilex.kurir.data.model.Auth
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class ProfileWrapper(private val repository: UsersRepository?) : Wrapper() {

    fun register(user: User): Emitter<Auth?> {
        return Emitter<Auth?>().also { result ->
            repository?.register(user) {
                result.value = it
            }
        }
    }
}