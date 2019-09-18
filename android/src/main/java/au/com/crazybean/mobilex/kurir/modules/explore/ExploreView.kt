package au.com.crazybean.mobilex.kurir.modules.explore

import au.com.crazybean.foundation.navigator.Arguments
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.View

interface ExploreView : View {
    fun showResult(users: List<User>)
    fun showDetails(arguments: Arguments)
    fun showEmpty()
}