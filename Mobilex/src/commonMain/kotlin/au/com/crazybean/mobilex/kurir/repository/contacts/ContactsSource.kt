package au.com.crazybean.mobilex.kurir.repository.contacts

interface ContactsSource {
    fun getContacts(myEmail: String, completion: (List<String>?) -> Unit)
    fun addContact(myEmail: String, email: String, completion: (Boolean)-> Unit)
    fun removeContact(myEmail: String, email: String, completion: (Boolean) -> Unit)
}