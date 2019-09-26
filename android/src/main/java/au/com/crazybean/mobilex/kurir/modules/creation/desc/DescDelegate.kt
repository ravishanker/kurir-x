package au.com.crazybean.mobilex.kurir.modules.creation.desc

import android.media.Image
import android.os.Bundle
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.mobilex.kurir.data.model.Address
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.Module
import au.com.crazybean.mobilex.kurir.modules.base.arguments
import au.com.crazybean.mobilex.kurir.modules.base.with

class DescDelegate(view: DescView?,
                   viewModel: DescViewModel) : Delegate<DescView, DescViewModel>(view, viewModel) {

    private var dest: Address? = null
    private var desc: String? = null

    override fun onCreate(params: Bundle) {
        super.onCreate(params)
        checkInput()
    }

    fun onDestUpdate(query: String) {
        // Parse the query.
        query.parse?.let {
            dest = it
        }
        checkInput()
    }

    fun onImageAdded(image: Image) {
    }

    fun onDescUpdate(desc: String) {
        this.desc = desc
        checkInput()
    }

    fun onNextClick() {
        dest?.takeIf { checkInput() }?.let {
            val arguments = Module.Receiver.arguments
                .with(Extra.DESC, desc?: "")
                .with(Extra.DEST, it, Address.serializer())
            view?.showReceiver(arguments)
        }
    }

    private fun checkInput(): Boolean {
        return if (dest == null || desc.isNullOrEmpty()) {
            view?.hideNext()
            false
        } else {
            view?.showNext()
            true
        }
    }

    private val String.parse: Address?
        get() = split(",").takeIf { it.size >= 3 }?.let {
            Address(city = it[0], state = it[1], country = it[2])
        }
}