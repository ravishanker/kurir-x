package au.com.crazybean.mobilex.kurir.modules.creation.desc

import au.com.crazybean.mobilex.foundation.saw.Worker
import au.com.crazybean.mobilex.foundation.saw.awareness.Emitter
import au.com.crazybean.mobilex.kurir.data.model.Address
import au.com.crazybean.mobilex.kurir.repository.geo.GeoRepository

class DescWorker(private val repository: GeoRepository?) : Worker() {
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