package au.com.crazybean.mobilex.kurir.repository.contacts

import au.com.crazybean.mobilex.foundation.repository.Repository

class ContactsRepository(private val contactsSource: ContactsSource) : Repository() {
    fun getContacts(email: String, completion: (List<String>?) -> Unit) {
        contactsSource.getContacts(email, completion)
    }

    fun addContact(myEmail: String, email: String, completion: (Boolean)-> Unit) {
        contactsSource.addContact(myEmail, email, completion)
    }
}