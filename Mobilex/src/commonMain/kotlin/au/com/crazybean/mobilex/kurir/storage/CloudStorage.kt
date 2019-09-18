package au.com.crazybean.mobilex.kurir.storage

interface CloudStorage {
    fun readData(paths: String, onComplete: (Map<String, Any?>?, Throwable?) -> Unit)
    fun readArray(paths: String, onComplete: (List<Map<String, Any?>>?, Throwable?) -> Unit)
    fun writeData(paths: String, payload: Map<String, Any?>, onComplete: (Boolean, Throwable?) -> Unit)
    fun delete(paths: String, onComplete: (Boolean, Throwable?) -> Unit)
}