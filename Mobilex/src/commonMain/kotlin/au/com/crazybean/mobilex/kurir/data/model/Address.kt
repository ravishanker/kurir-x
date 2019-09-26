package au.com.crazybean.mobilex.kurir.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Address(val street: String? = null,
                   val suburb: String? = null,
                   val postal: String? = null,
                   val city: String? = null,
                   val state: String? = null,
                   val country: String? = null)