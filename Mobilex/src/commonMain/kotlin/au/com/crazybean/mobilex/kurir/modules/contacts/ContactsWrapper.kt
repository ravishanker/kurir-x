package au.com.crazybean.mobilex.kurir.modules.contacts

import au.com.crazybean.mobilex.foundation.saw.Wrapper
import au.com.crazybean.mobilex.foundation.saw.pulse.LiveData
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.kEmail
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.contacts.ContactsRepository
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class ContactsWrapper(private val userData: UserData?,
                      private val usersRepository: UsersRepository?,
                      private val contactsRepository: ContactsRepository?) : Wrapper() {
    val contacts: LiveData<List<User>?>
        get() = LiveData<List<User>?>().also { result ->
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