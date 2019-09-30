package au.com.crazybean.mobilex.kurir.modules.creation.receiver

import android.os.Bundle
import androidx.lifecycle.Observer
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.mobilex.kurir.data.model.Address
import au.com.crazybean.mobilex.kurir.data.model.Contact
import au.com.crazybean.mobilex.kurir.data.model.Parcel
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.fetch

class ReceiverDelegate(view: ReceiverView?,
                       viewModel: ReceiverViewModel) : Delegate<ReceiverView, ReceiverViewModel>(view, viewModel) {

    private var desc: String? = null
    private var dest: Address? = null
    private var origin: Address? = null

    override fun onCreate(params: Bundle) {
        super.onCreate(params)

        origin = params.fetch(Extra.ORIG, Address.serializer())
        dest = params.fetch(Extra.DEST, Address.serializer())
        desc = params.getString(Extra.DESC, "")
    }

    fun onPlaceClick(name: String?, mobile: String?, email: String?) {
        when {
            name.isNullOrBlank() -> view?.showNoName()
            mobile.isNullOrBlank() && email.isNullOrBlank() -> view?.showNoContact()
            else -> dest?.let {
                val parcel = Parcel(desc?: "", "Mobile", "PENDING", null)
                val receiver = Contact(name, mobile?: "", email)
                view?.showSpinner()
                viewModel.createTask(origin!!, it, parcel, receiver)
                    .observe(this, Observer {
                        view?.hideSpinner()
                        view?.dismiss()
                    })
            }
        }
    }
}