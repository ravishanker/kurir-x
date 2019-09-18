package au.com.crazybean.mobilex.kurir.modules.details

import au.com.crazybean.mobilex.kurir.data.model.Task
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.View

interface DetailsView : View {
    fun showUser(user: User)
    fun showTask(task: Task)
    fun showError()
}