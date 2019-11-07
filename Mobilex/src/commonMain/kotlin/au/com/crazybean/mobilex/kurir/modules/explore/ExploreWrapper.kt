package au.com.crazybean.mobilex.kurir.modules.explore

import au.com.crazybean.mobilex.foundation.saw.Wrapper
import au.com.crazybean.mobilex.foundation.saw.awareness.Emitter
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class ExploreWrapper(private val repository: UsersRepository?) : Wrapper() {
    private val liveData by lazy {
        Emitter<List<User>?>()
    }

    fun getContacts(query: String?): Emitter<List<User>?> {
        return liveData.also {
            repository?.getUsers(null) { users ->
                it.value = users?.filter {
                    query.isNullOrEmpty() || it.firstName?.contains(query, true) == true || it.lastName?.contains(query, true) == true
                }
            }
        }
    }
}