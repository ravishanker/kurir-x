package au.com.crazybean.mobilex.kurir.modules.contacts

import au.com.crazybean.mobilex.foundation.saw.Worker
import au.com.crazybean.mobilex.foundation.saw.awareness.Emitter
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.kEmail
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.contacts.ContactsRepository
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class ContactsWorker(private val userData: UserData?,
                     private val usersRepository: UsersRepository?,
                     private val contactsRepository: ContactsRepository?) : Worker() {
    val contacts: Emitter<List<User>?>
        get() = Emitter<List<User>?>().also { result ->
            val email = userData?.getString(kEmail)?: ""
            contactsRepository?.getContacts(email) { emails ->
                emails?.takeIf { it.isNotEmpty() }?.let { filters ->
                    usersRepository?.getUsers(filters) {
                        result.value = it
                    }
                }?: run {
                    result.value = null
                }
            }
        }
}