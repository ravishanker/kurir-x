package au.com.crazybean.mobilex.kurir.modules.details

import android.os.Bundle
import androidx.lifecycle.Observer
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.mobilex.kurir.data.model.Task
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.Module
import au.com.crazybean.mobilex.kurir.modules.base.arguments
import au.com.crazybean.mobilex.kurir.modules.base.fetch

class DetailsDelegate(view: DetailsView?,
                      viewModel: DetailsViewModel) : Delegate<DetailsView, DetailsViewModel>(view, viewModel) {

    private var task: Task? = null
    private var user: User? = null

    override fun onCreate(params: Bundle) {
        super.onCreate(params)

        params.fetch(Extra.USER, User.serializer())?.let {
            user = it
            view?.showUser(it)
        }?: params.fetch(Extra.TASK, Task.serializer())?.let {
            task = it
            view?.showTask(it)
        }
    }

    fun onButtonClick() {
        user?.let {
            viewModel.addContact(it)
                .observe(this, Observer { success ->
                    if (success) {
                        view?.dismiss()
                    } else {
                        view?.showError()
                    }
                })
        }
    }
}