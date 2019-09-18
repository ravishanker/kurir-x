package au.com.crazybean.mobilex.kurir.modules.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.crazybean.foundation.mvvm.ViewModel
import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.data.kEmail
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.repository.contacts.ContactsRepository

class DetailsViewModel(private val userData: UserData?,
                       private val repository: ContactsRepository?) : ViewModel() {
    fun addContact(user: User): LiveData<Boolean> {
        return MutableLiveData<Boolean>().also { liveData ->
            userData?.getString(kEmail, "")?.takeIf { it.isNotEmpty() }?.let { myEmail ->
                repository?.addContact(myEmail, user.email?: "") {
                    liveData.value = it
                }
            }?: liveData.postValue(false)
        }
    }
}