package au.com.crazybean.mobilex.kurir.modules.auth.profile

import au.com.crazybean.mobilex.foundation.saw.Wrapper
import au.com.crazybean.mobilex.foundation.saw.pulse.LiveData
import au.com.crazybean.mobilex.kurir.data.model.Auth
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class ProfileWrapper(private val repository: UsersRepository?) : Wrapper() {

    fun register(user: User): LiveData<Auth?> {
        return LiveData<Auth?>().also { result ->
            repository?.register(user) {
                result.value = it
            }
        }
    }
}