package au.com.crazybean.mobilex.kurir.impl

import au.com.crazybean.mobilex.kurir.storage.CloudStorage
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseStorage : CloudStorage {
    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun readData(paths: String, onComplete: (Map<String, Any?>?, Throwable?) -> Unit) {
        firestore.document(paths)
            .get()
            .addOnSuccessListener { snapshot ->
                onComplete(snapshot.data, null)
            }
            .addOnFailureListener {
                onComplete(null, it)
            }
    }

    override fun readArray(paths: String, onComplete: (List<Map<String, Any?>>?, Throwable?) -> Unit) {
        firestore.collection(paths)
            .get()
            .addOnSuccessListener {
                val entities = it.map { document ->
                    document.data
                }
                onComplete(entities, null)
            }
            .addOnFailureListener {
                onComplete(null, it)
            }
    }

    override fun writeData(paths: String, payload: Map<String, Any?>, onComplete: (Boolean, Throwable?) -> Unit) {
        firestore.document(paths)
            .set(payload)
            .addOnSuccessListener {
                onComplete(true, null)
            }
            .addOnFailureListener {
                onComplete(false, it)
            }
    }

    override fun delete(paths: String, onComplete: (Boolean, Throwable?) -> Unit) {
        firestore.document(paths)
            .delete()
            .addOnSuccessListener {
                onComplete(true, null)
            }
            .addOnFailureListener {
                onComplete(false, null)
            }
    }
}