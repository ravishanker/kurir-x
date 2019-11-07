package au.com.crazybean.mobilex.kurir.modules.base

import androidx.lifecycle.ViewModel
import au.com.crazybean.mobilex.foundation.saw.Wrapper

class ViewModel<out WRAPPER: Wrapper>(val wrapper: WRAPPER) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        wrapper.onRelease()
    }
}