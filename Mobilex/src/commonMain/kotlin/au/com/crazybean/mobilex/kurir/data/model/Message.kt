package au.com.crazybean.mobilex.kurir.data.model

data class Message(val from: String,
                   val to: String,
                   val timestamp: Long,
                   val message: String)