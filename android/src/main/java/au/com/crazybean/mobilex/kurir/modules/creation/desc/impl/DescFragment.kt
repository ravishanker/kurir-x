package au.com.crazybean.mobilex.kurir.modules.creation.desc.impl

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.mobilex.kurir.R
import au.com.crazybean.mobilex.kurir.data.model.Address
import au.com.crazybean.mobilex.kurir.modules.base.BaseFragment
import au.com.crazybean.mobilex.kurir.modules.base.watcher
import au.com.crazybean.mobilex.kurir.modules.creation.desc.DescDelegate
import au.com.crazybean.mobilex.kurir.modules.creation.desc.DescView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import android.content.Intent

class DescFragment : BaseFragment<DescDelegate>(), DescView {
    private val kRequestPickImage = 0x200

    override val delegate: DescDelegate? by inject {
        parametersOf(this)
    }

    override val layoutRes: Int
        get() = R.layout.fragment_desc

    private val originAdapter by lazy {
        context?.let {
            ArrayAdapter<String>(it, android.R.layout.select_dialog_item)
        }
    }

    private val destAdapter by lazy {
        context?.let {
            ArrayAdapter<String>(it, android.R.layout.select_dialog_item)
        }
    }

    private var adapter: ArrayAdapter<String>? = null

    override fun onViewLoad(layout: ViewGroup) {
        super.onViewLoad(layout)
        layout.findViewById<View>(R.id.next_button)?.setOnClickListener {
            delegate?.onNextClick()
        }

        layout.findViewById<EditText>(R.id.desc_input_field)?.watcher = { _, desc ->
            delegate?.onDescUpdate(desc)
        }

        layout.findViewById<View>(R.id.take_photo_icon)?.setOnClickListener {
            delegate?.onAddImageClick()
        }

        layout.findViewById<AutoCompleteTextView?>(R.id.origin_auto_complete)?.also {
            it.setAdapter(originAdapter)
            it.watcher = { _, query ->
                adapter = originAdapter
                delegate?.onQuery(query)
            }

            it.setOnItemClickListener { _, _, position, _ ->
                delegate?.onOriginSelect(position)
            }
        }

        layout.findViewById<AutoCompleteTextView?>(R.id.dest_auto_complete)?.also {
            it.setAdapter(destAdapter)
            it.watcher = { _, query ->
                adapter = destAdapter
                delegate?.onQuery(query)
            }

            it.setOnItemClickListener { _, _, position, _ ->
                delegate?.onDestSelect(position)
            }
        }
    }

    override fun showNext() {
        view?.findViewById<View>(R.id.next_button)?.visibility = View.VISIBLE
    }

    override fun hideNext() {
        view?.findViewById<View>(R.id.next_button)?.visibility = View.INVISIBLE
    }

    override fun showReceiver(arguments: Arguments) {
        navigate(arguments)
    }

    override fun showWrongDest() {
        showError(R.string.error_wrong_city)
    }

    override fun showAutoList(cities: List<Address>) {
        adapter?.clear()
        adapter?.addAll(cities.map {
            "${it.city}, ${it.state}, ${it.country}"
        })
        adapter?.notifyDataSetChanged()
    }

    override fun showPicker() {
        Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }.let {
            startActivityForResult(Intent.createChooser(it, getString(R.string.title_pick_image)), kRequestPickImage)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}