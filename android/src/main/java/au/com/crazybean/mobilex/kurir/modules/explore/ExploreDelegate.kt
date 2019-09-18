package au.com.crazybean.mobilex.kurir.modules.explore

import androidx.lifecycle.Observer
import au.com.crazybean.foundation.mvvm.Delegate
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.Module
import au.com.crazybean.mobilex.kurir.modules.base.arguments
import au.com.crazybean.mobilex.kurir.modules.base.with

class ExploreDelegate(view: ExploreView?,
                      viewModel: ExploreViewModel) : Delegate<ExploreView, ExploreViewModel>(view, viewModel) {
    fun onQuery(query: String?) {
        viewModel.getContacts(query)
            .observe(this, Observer { result ->
                result?.takeIf { it.isNotEmpty() }?.let { users ->
                    view?.showResult(users)
                }?: view?.showEmpty()
            })
    }

    fun onContactClick(user: User) {
        val arguments = Module.Details.arguments
            .with(Extra.USER, user, User.serializer())
        view?.showDetails(arguments)
        view?.dismiss()
    }
}