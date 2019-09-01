package au.com.crazybean.mobilex.kurir.impl

import au.com.crazybean.mobilex.database.Database
import com.google.firebase.firestore.FirebaseFirestore

class DatabaseImpl : Database {
    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun readData(tableName: String, onSuccess: (List<Map<String, Any?>>) -> Unit, onError: ((Throwable) -> Unit)?) {
        firestore.collection(tableName)
                .get()
                .addOnSuccessListener {
                    val entities = it.map { document ->
                        document.data
                    }
                    onSuccess(entities)
                }
                .addOnFailureListener {
                    onError?.invoke(it)
                }
    }

    override fun writeData(tableName: String, payload: Map<String, Any?>, onSuccess: (String) -> Unit, onError: ((Throwable) -> Unit)?) {
        firestore.collection(tableName)
                .add(payload)
                .addOnSuccessListener {
                    onSuccess(it.id)
                }
                .addOnFailureListener {
                    onError?.invoke(it)
                }
    }
}