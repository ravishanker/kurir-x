package au.com.crazybean.mobilex.kurir.modules.creation.desc

import android.media.Image
import android.os.Bundle
import androidx.lifecycle.Observer
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.mobilex.kurir.data.model.Address
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.Module
import au.com.crazybean.mobilex.kurir.modules.base.arguments
import au.com.crazybean.mobilex.kurir.modules.base.with

class DescDelegate(view: DescView?,
                   viewModel: DescViewModel) : Delegate<DescView, DescViewModel>(view, viewModel) {

    private var origin: Address? = null
    private var dest: Address? = null
    private var desc: String? = null
    private var options: List<Address>? = null

    override fun onCreate(params: Bundle) {
        super.onCreate(params)
        checkInput()
    }

    fun onQuery(query: String) {
        viewModel.findCities(query)
            .observe(this, Observer { cities ->
                cities?.takeIf { it.isNotEmpty() }?.let {
                    options = it
                    view?.showAutoList(it)
                }
            })
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
        view?.showPicker()
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
                .with(Extra.ORIG, origin!!, Address.serializer())
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
}