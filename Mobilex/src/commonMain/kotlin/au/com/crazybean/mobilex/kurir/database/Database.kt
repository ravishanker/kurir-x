package au.com.crazybean.mobilex.kurir.database

interface Database {
    fun readData(tableName: String, onSuccess: (List<Map<String, Any?>>) -> Unit, onError: ((Throwable) -> Unit)? = null)
    fun writeData(tableName: String, payload: Map<String, Any?>, onSuccess: (String) -> Unit, onError: ((Throwable) -> Unit)? = null)
}