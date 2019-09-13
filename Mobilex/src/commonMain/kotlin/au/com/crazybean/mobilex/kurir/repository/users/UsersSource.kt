package au.com.crazybean.mobilex.kurir.repository.users

import au.com.crazybean.mobilex.kurir.data.model.User

interface UsersSource {
    fun getUser(mobile: String?, email: String?, callback: (User?)-> Unit)
    fun addUser(user: User, callback: (User?)-> Unit)
}