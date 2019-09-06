package au.com.crazybean.mobilex.kurir.data.model

import kotlinx.serialization.Serializable

const val ERR_NONE = 0
const val ERR_NOT_FOUND = -1
const val ERR_PASSWORD = -2
const val ERR_EXISTS = -3

@Serializable
data class Auth(val result: Int,
                val error: String? = null)