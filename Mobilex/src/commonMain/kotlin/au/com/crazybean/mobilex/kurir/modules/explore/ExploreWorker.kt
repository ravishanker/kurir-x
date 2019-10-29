package au.com.crazybean.mobilex.kurir.modules.explore

import au.com.crazybean.mobilex.foundation.saw.Worker
import au.com.crazybean.mobilex.foundation.saw.awareness.Emitter
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class ExploreWorker(private val repository: UsersRepository?) : Worker() {
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