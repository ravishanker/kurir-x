package au.com.crazybean.mobilex.kurir.repository.geo

import au.com.crazybean.mobilex.kurir.data.model.Address

interface GeoSource {
    suspend fun findCities(query: String): List<Address>?
}