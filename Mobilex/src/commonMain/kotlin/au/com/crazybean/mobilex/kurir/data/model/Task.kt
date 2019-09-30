package au.com.crazybean.mobilex.kurir.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Task(val origin: Address,
                val dest: Address,
                val owner: String,
                val parcel: Parcel,
                val timestamp: Long,
                val picker: String? = null,
                val recipient: Contact? = null)