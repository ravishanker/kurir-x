package au.com.crazybean.mobilex.kurir.modules.base

import androidx.lifecycle.ViewModel
import au.com.crazybean.mobilex.foundation.saw.Worker

class ViewModel<out WORKER: Worker>(val worker: WORKER) : ViewModel()