package au.com.crazybean.mobilex.kurir.modules.details

import au.com.crazybean.mobilex.foundation.saw.Wrapper
import au.com.crazybean.mobilex.foundation.saw.pulse.LiveData
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.kEmail
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.contacts.ContactsRepository

class DetailsWrapper(private val userData: UserData?,
                     private val repository: ContactsRepository?) : Wrapper() {
    fun addContact(user: User): LiveData<Boolean> {
        return LiveData<Boolean>().also { result ->
            userData?.getString(kEmail, "")?.takeIf { it.isNotEmpty() }?.let { myEmail ->
                repository?.addContact(myEmail, user.email?: "") {
                    result.value = it
                }
            }?: run {
                result.value = false
            }
        }
    }
}