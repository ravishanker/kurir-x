package au.com.crazybean.mobilex.kurir.repository.contacts

interface ContactsSource {
    fun getContacts(myEmail: String, callback: (List<String>?) -> Unit)
    fun addContact(myEmail: String, email: String, callback: (Boolean)-> Unit)
    fun removeContact(myEmail: String, email: String, callback: (Boolean) -> Unit)
}