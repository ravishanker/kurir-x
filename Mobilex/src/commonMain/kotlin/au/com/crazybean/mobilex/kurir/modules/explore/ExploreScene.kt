package au.com.crazybean.mobilex.kurir.modules.explore

import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.Scene

interface ExploreScene : Scene {
    fun showResult(users: List<User>)
    fun showDetails(user: User)
    fun showEmpty()
}