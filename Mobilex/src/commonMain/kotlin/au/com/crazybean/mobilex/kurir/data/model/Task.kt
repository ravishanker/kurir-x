package au.com.crazybean.mobilex.kurir.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(val from: String,
                val status: String? = null,
                val actor: String? = null)