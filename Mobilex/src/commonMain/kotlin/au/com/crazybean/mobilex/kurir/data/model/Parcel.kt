package au.com.crazybean.mobilex.kurir.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Parcel(val desc: String,
                  val category: String,
                  val status: String,
                  val imageUrls: List<String>? = null)