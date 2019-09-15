package au.com.crazybean.mobilex.kurir.repository.users

import au.com.crazybean.mobilex.kurir.data.model.Enroll
import au.com.crazybean.mobilex.kurir.data.model.User

interface UsersSource {
    fun getUser(mobile: String?, email: String?, callback: (User?)-> Unit)
    fun addUser(user: User, callback: (User?)-> Unit)
    fun addEnroll(enroll: Enroll, callback: (Enroll?) -> Unit)
    fun getEnroll(token: String, callback: (Enroll?) -> Unit)
}