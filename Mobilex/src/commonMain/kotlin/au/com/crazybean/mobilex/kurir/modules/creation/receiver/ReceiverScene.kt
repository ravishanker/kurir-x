package au.com.crazybean.mobilex.kurir.modules.creation.receiver

import au.com.crazybean.mobilex.kurir.modules.base.Scene

interface ReceiverScene : Scene {
    fun showNoName()
    fun showNoContact()
    fun showSpinner()
    fun hideSpinner()
}