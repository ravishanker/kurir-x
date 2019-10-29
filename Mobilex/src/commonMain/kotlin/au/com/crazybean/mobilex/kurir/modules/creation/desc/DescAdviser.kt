package au.com.crazybean.mobilex.kurir.modules.creation.desc

import au.com.crazybean.mobilex.foundation.saw.Adviser
import au.com.crazybean.mobilex.kurir.data.model.Address
import io.ktor.http.ContentType

class DescAdviser(scene: DescScene?,
                  worker: DescWorker) : Adviser<DescScene, DescWorker>(scene, worker) {

    private var origin: Address? = null
    private var dest: Address? = null
    private var desc: String? = null
    private var options: List<Address>? = null

    override fun onLoad(params: Map<String, Any?>) {
        super.onLoad(params)
        checkInput()
    }

    fun onQuery(query: String) {
        worker.findCities(query)
            .observe(this) { cities ->
                cities?.takeIf { it.isNotEmpty() }?.let {
                    options = it
                    scene?.showAutoList(it)
                }
            }
    }

    fun onOriginSelect(position: Int) {
        options?.takeIf { position < it.size }?.let {
            origin = it[position]
            checkInput()
        }
    }

    fun onDestSelect(position: Int) {
        options?.takeIf { position < it.size }?.let {
            dest = it[position]
            checkInput()
        }
    }

    fun onAddImageClick() {
        scene?.showPicker()
    }

    fun onImageAdded(image: ContentType.Image) {
    }

    fun onDescUpdate(desc: String) {
        this.desc = desc
        checkInput()
    }

    fun onNextClick() {
        dest?.takeIf { checkInput() }?.let {
            scene?.showReceiver(desc?: "", origin!!, it)
        }
    }

    private fun checkInput(): Boolean {
        return if (dest == null || desc.isNullOrEmpty()) {
            scene?.hideNext()
            false
        } else {
            scene?.showNext()
            true
        }
    }
}