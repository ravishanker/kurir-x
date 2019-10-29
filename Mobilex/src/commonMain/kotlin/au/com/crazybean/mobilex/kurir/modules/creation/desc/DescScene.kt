package au.com.crazybean.mobilex.kurir.modules.creation.desc

import au.com.crazybean.mobilex.kurir.data.model.Address
import au.com.crazybean.mobilex.kurir.modules.base.Scene

interface DescScene : Scene {
    fun showNext()
    fun hideNext()
    fun showReceiver(desc: String, origin: Address, dest: Address)
    fun showWrongDest()
    fun showAutoList(cities: List<Address>)
    fun showPicker()
}