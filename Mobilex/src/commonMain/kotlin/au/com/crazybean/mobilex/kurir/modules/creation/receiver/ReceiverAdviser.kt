package au.com.crazybean.mobilex.kurir.modules.creation.receiver

import au.com.crazybean.mobilex.foundation.saw.Adviser
import au.com.crazybean.mobilex.kurir.data.model.Address
import au.com.crazybean.mobilex.kurir.data.model.Contact
import au.com.crazybean.mobilex.kurir.data.model.Parcel
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.fetch

class ReceiverAdviser(scene: ReceiverScene?,
                      worker: ReceiverWorker) : Adviser<ReceiverScene, ReceiverWorker>(scene, worker) {

    private var desc: String? = null
    private var dest: Address? = null
    private var origin: Address? = null

    override fun onLoad(params: Map<String, Any?>) {
        super.onLoad(params)

        origin = params.fetch(Extra.ORIG, Address.serializer())
        dest = params.fetch(Extra.DEST, Address.serializer())
        desc = (params[Extra.DESC]?.takeIf { it is String } as String?)?: ""
    }

    fun onPlaceClick(name: String?, mobile: String?, email: String?) {
        when {
            name.isNullOrBlank() -> scene?.showNoName()
            mobile.isNullOrBlank() && email.isNullOrBlank() -> scene?.showNoContact()
            else -> dest?.let {
                val parcel = Parcel(desc?: "", "Mobile", "PENDING", null)
                val receiver = Contact(name, mobile?: "", email)
                scene?.showSpinner()
                worker.createTask(origin!!, it, parcel, receiver).observe(this) {
                        scene?.hideSpinner()
                        scene?.dismiss()
                    }
            }
        }
    }
}