package au.com.crazybean.mobilex.kurir.impl

import au.com.crazybean.mobilex.kurir.storage.CloudStorage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FirebaseStorage : CloudStorage {
    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun readData(tableName: String, filters: Map<String, Any?>?, onSuccess: (List<Map<String, Any?>>) -> Unit, onError: (Throwable) -> Unit) {
        val reference = firestore.collection(tableName)
        var query: Query? = null
        filters?.takeIf { it.isNotEmpty() }?.forEach { filter ->
            query = (query?: reference).whereEqualTo(filter.key, filter.value)
        }

        (query?: firestore.collection(tableName)).get()
            .addOnSuccessListener {
                val entities = it.map { document ->
                    document.data
                }
                onSuccess(entities)
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    override fun writeData(tableName: String, filters: Map<String, Any?>?, payload: Map<String, Any?>, onSuccess: (String) -> Unit, onError: (Throwable) -> Unit) {
        (payload["id"] as String?)?.takeIf { it.isNotBlank() }?.let { identifier ->
            firestore.collection(tableName)
                .document(identifier)
                .set(payload)
                .addOnSuccessListener {
                    onSuccess(identifier)
                }
                .addOnFailureListener {
                    onError(it)
                }
        }?: firestore.collection(tableName)
            .add(payload)
            .addOnSuccessListener {
                onSuccess(it.id)
            }
            .addOnFailureListener {
                onError(it)
            }
    }
}