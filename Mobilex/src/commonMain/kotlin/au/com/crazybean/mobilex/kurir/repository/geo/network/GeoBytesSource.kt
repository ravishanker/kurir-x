package au.com.crazybean.mobilex.kurir.repository.geo.network

import au.com.crazybean.mobilex.foundation.extension.decode
import au.com.crazybean.mobilex.foundation.network.HttpConfig
import au.com.crazybean.mobilex.foundation.network.HttpEngine
import au.com.crazybean.mobilex.kurir.data.model.Address
import au.com.crazybean.mobilex.kurir.repository.geo.GeoSource
import io.ktor.client.request.get
import kotlinx.serialization.list
import kotlinx.serialization.serializer

private val httpConfig = HttpConfig("gd.geobytes.com", 0, false)

class GeoBytesSource : HttpEngine(httpConfig), GeoSource {
    override suspend fun findCities(query: String): List<Address>? {
        val url = "http://gd.geobytes.com/autocompletecity?q=$query"
        return httpClient.get<String>(urlString = url).decode(String.serializer().list)?.takeIf {
            it.isNotEmpty()
        }?.mapNotNull { raw ->
            raw.split(",").takeIf { it.size >= 3 }?.let { segments ->
                Address(city = segments[0].trim().capitalize(), state = segments[1].trim().capitalize(), country = segments[2].trim().capitalize())
            }
        }
    }
}