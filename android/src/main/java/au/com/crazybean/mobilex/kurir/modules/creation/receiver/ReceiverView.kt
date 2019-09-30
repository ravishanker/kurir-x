package au.com.crazybean.mobilex.kurir.modules.creation.receiver

import au.com.crazybean.mobilex.kurir.modules.base.View

interface ReceiverView : View {
    fun showNoName()
    fun showNoContact()
    fun showSpinner()
    fun hideSpinner()
}