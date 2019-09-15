package au.com.crazybean.mobilex.kurir.storage

interface CloudStorage {
    fun readData(tableName: String, filters: Map<String, Any?>?, onSuccess: (List<Map<String, Any?>>) -> Unit, onError: (Throwable) -> Unit = {})
    fun writeData(tableName: String, filters: Map<String, Any?>?, payload: Map<String, Any?>, onSuccess: (String) -> Unit, onError: (Throwable) -> Unit = {})
}