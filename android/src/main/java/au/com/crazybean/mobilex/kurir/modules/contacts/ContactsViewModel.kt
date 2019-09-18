package au.com.crazybean.mobilex.kurir.modules.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.crazybean.foundation.mvvm.ViewModel
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.kEmail
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.contacts.ContactsRepository
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository

class ContactsViewModel(private val userData: UserData?,
                        private val usersRepository: UsersRepository?,
                        private val contactsRepository: ContactsRepository?) : ViewModel() {
    val contacts: LiveData<List<User>?>
        get() = MutableLiveData<List<User>?>().also { liveData ->
            val email = userData?.getString(kEmail)?: ""
            contactsRepository?.getContacts(email) { emails ->
                emails?.takeIf { it.isNotEmpty() }?.let { filters ->
                    usersRepository?.getUsers(filters) {
                        liveData.value = it
                    }
                }?: liveData.postValue(null)
            }
        }
}