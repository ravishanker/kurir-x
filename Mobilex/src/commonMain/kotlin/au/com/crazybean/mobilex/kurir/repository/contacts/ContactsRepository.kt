package au.com.crazybean.mobilex.kurir.repository.contacts

import au.com.crazybean.mobilex.foundation.repository.Repository

class ContactsRepository(private val contactsSource: ContactsSource) : Repository() {
    fun getContacts(email: String, callback: (List<String>?) -> Unit) {
        contactsSource.getContacts(email, callback)
    }

    fun addContact(myEmail: String, email: String, callback: (Boolean)-> Unit) {
        contactsSource.addContact(myEmail, email, callback)
    }
}