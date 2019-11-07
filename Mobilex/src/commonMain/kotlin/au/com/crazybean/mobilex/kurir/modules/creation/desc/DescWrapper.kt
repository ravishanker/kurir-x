package au.com.crazybean.mobilex.kurir.modules.creation.desc

import au.com.crazybean.mobilex.foundation.saw.Wrapper
import au.com.crazybean.mobilex.foundation.saw.awareness.Emitter
import au.com.crazybean.mobilex.kurir.data.model.Address
import au.com.crazybean.mobilex.kurir.repository.geo.GeoRepository

class DescWrapper(private val repository: GeoRepository?) : Wrapper() {
    private val liveData by lazy {
        Emitter<List<Address>?>()
    }

    fun findCities(query: String): Emitter<List<Address>?> {
        return liveData.also {
            repository?.findCities(query) { result ->
                it.value = result
            }
        }
    }
}