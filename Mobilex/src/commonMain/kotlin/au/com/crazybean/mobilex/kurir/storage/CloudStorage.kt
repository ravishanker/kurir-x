package au.com.crazybean.mobilex.kurir.storage

interface CloudStorage {
    fun readData(paths: String, completion: (Map<String, Any?>?, Throwable?) -> Unit)
    fun writeData(paths: String, payload: Map<String, Any?>, completion: (Boolean, Throwable?) -> Unit)
    fun delete(paths: String, completion: (Boolean, Throwable?) -> Unit)
    fun readArray(paths: String,
                  completion: (List<Map<String, Any?>>?, Throwable?) -> Unit,
                  observation: ((List<Map<String, Any?>>?, List<Map<String, Any?>>?, List<Map<String, Any?>>?, Throwable?) -> Unit)? = null)
}