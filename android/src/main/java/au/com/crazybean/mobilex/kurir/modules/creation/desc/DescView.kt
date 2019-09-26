package au.com.crazybean.mobilex.kurir.modules.creation.desc

import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.mobilex.kurir.modules.base.View

interface DescView : View {
    fun showNext()
    fun hideNext()
    fun showReceiver(arguments: Arguments)
    fun showWrongtDest()
}