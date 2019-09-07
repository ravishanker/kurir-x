package au.com.crazybean.mobilex.kurir.repository.users

import au.com.crazybean.mobilex.kurir.data.model.User

interface UsersSource {
    suspend fun getUser(mobile: String?, email: String?): User?
    suspend fun addUser(user: User): User?
}