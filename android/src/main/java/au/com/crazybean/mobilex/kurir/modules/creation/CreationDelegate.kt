package au.com.crazybean.mobilex.kurir.modules.creation

import android.os.Bundle
import au.com.crazybean.foundation.mvvm.Delegate

class CreationDelegate(view: CreationView?,
                       viewModel: CreationViewModel) : Delegate<CreationView, CreationViewModel>(view, viewModel) {
    override fun onCreate(params: Bundle) {
        super.onCreate(params)
        view?.showCreation()
    }
}