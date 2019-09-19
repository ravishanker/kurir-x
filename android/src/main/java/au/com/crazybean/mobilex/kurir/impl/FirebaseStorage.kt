package au.com.crazybean.mobilex.kurir.impl

import au.com.crazybean.mobilex.foundation.logger.Logger
import au.com.crazybean.mobilex.kurir.storage.CloudStorage
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseStorage : CloudStorage {
    private val firestore by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun readData(paths: String, completion: (Map<String, Any?>?, Throwable?) -> Unit) {
        firestore.document(paths)
            .get()
            .addOnSuccessListener { snapshot ->
                completion(snapshot.data, null)
            }
            .addOnFailureListener {
                completion(null, it)
            }
    }

    override fun writeData(paths: String, payload: Map<String, Any?>, completion: (Boolean, Throwable?) -> Unit) {
        firestore.document(paths)
            .set(payload)
            .addOnSuccessListener {
                completion(true, null)
            }
            .addOnFailureListener {
                completion(false, it)
            }
    }

    override fun delete(paths: String, completion: (Boolean, Throwable?) -> Unit) {
        firestore.document(paths)
            .delete()
            .addOnSuccessListener {
                completion(true, null)
            }
            .addOnFailureListener {
                completion(false, null)
            }
    }

    override fun readArray(paths: String,
                           completion: (List<Map<String, Any?>>?, Throwable?) -> Unit,
                           observation: ((List<Map<String, Any?>>?, List<Map<String, Any?>>?, List<Map<String, Any?>>?, Throwable?) -> Unit)?) {
        firestore.collection(paths).also { reference ->
            observation?.let { callback ->
                reference.addSnapshotListener { snapshot, exception ->
                    snapshot?.documentChanges?.takeIf { it.isNotEmpty() }?.let { changes ->
                        val added = changes.filter { it.type == DocumentChange.Type.ADDED }.map {
                            it.document.data
                        }

                        val modified = changes.filter { it.type == DocumentChange.Type.MODIFIED }.map {
                            it.document.data
                        }

                        val removed = changes.filter { it.type == DocumentChange.Type.REMOVED }.map {
                            it.document.data
                        }

                        callback(added, modified, removed, exception)
                    }?: Logger.d(exception)
                }
            }
        }.get()
            .addOnSuccessListener {
                val entities = it.map { document ->
                    document.data
                }
                completion(entities, null)
            }
            .addOnFailureListener {
                completion(null, it)
            }
    }
}