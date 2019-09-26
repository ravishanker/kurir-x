package au.com.crazybean.mobilex.kurir.modules.creation.receiver

import android.os.Bundle
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.mobilex.kurir.data.model.Address
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.fetch

class ReceiverDelegate(view: ReceiverView?,
                       viewModel: ReceiverViewModel) : Delegate<ReceiverView, ReceiverViewModel>(view, viewModel) {

    private var desc: String? = null
    private var dest: Address? = null

    override fun onCreate(params: Bundle) {
        super.onCreate(params)

        dest = params.fetch(Extra.DEST, Address.serializer())
        desc = params.getString(Extra.DESC, "")
    }
}