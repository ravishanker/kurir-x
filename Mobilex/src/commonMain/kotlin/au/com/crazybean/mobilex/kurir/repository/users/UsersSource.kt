package au.com.crazybean.mobilex.kurir.repository.users

import au.com.crazybean.mobilex.kurir.data.model.Enroll
import au.com.crazybean.mobilex.kurir.data.model.User

interface UsersSource {
    fun getUser(mobile: String?, email: String?, completion: (User?)-> Unit)
    fun addUser(user: User, completion: (User?)-> Unit)
    fun addEnroll(enroll: Enroll, completion: (Enroll?) -> Unit)
    fun getEnroll(token: String, completion: (Enroll?) -> Unit)
    fun getUsers(emails: List<String>?, completion: (List<User>?) -> Unit)
}