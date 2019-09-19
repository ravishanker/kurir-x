package au.com.crazybean.mobilex.kurir.repository.contacts.cloud

import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.kurir.repository.contacts.ContactsSource
import au.com.crazybean.mobilex.kurir.storage.CloudStorage

private const val TABLE_CONTACTS = "contacts"
private const val kEmail = "email"
private const val PATH = "entities"

class CloudContactsSource(private val storage: CloudStorage) : ContactsSource {
    override fun getContacts(myEmail: String, completion: (List<String>?) -> Unit) {
        storage.readArray("$TABLE_CONTACTS/$myEmail/$PATH", completion = { entities, _ ->
            entities?.map { it[kEmail] as String }?.let { emails ->
                completion(emails)
            }?: completion(null)
        })
    }

    override fun addContact(myEmail: String, email: String, completion: (Boolean) -> Unit) {
        storage.writeData("$TABLE_CONTACTS/$myEmail/$PATH/$email", mapOf(Pair(kEmail, email))) { success, throwable ->
            Logger.d(throwable)
            completion(success)
        }
    }

    override fun removeContact(myEmail: String, email: String, completion: (Boolean) -> Unit) {
        storage.delete("$TABLE_CONTACTS/$email") { success, throwable ->
            Logger.d(throwable)
            completion(success)
        }
    }
}