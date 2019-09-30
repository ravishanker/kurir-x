package au.com.crazybean.mobilex.kurir.repository.geo

import au.com.crazybean.mobilex.foundation.repository.Repository
import au.com.crazybean.mobilex.kurir.data.model.Address

class GeoRepository(private val geoSource: GeoSource?) : Repository() {
    fun findCities(query: String, completion: (List<Address>?) -> Unit) {
        execute(completion) {
            geoSource?.findCities(query)
        }
    }
}