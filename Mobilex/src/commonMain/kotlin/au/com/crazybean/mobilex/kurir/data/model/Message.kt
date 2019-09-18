package au.com.crazybean.mobilex.kurir.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Message(val from: String,
                   val to: String,
                   val content: String,
                   val type: Long,
                   val timestamp: Long)