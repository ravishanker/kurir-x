package au.com.crazybean.mobilex.kurir.modules.creation.desc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.crazybean.foundation.mvvm.ViewModel
import au.com.crazybean.mobilex.kurir.data.model.Address
import au.com.crazybean.mobilex.kurir.repository.geo.GeoRepository

class DescViewModel(private val repository: GeoRepository?) : ViewModel() {
    private val liveData by lazy {
        MutableLiveData<List<Address>?>()
    }

    fun findCities(query: String): LiveData<List<Address>?> {
        return liveData.also {
            repository?.findCities(query) { result ->
                it.value = result
            }
        }
    }
}