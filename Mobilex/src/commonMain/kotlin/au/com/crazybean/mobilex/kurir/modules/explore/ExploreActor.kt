package au.com.crazybean.mobilex.kurir.modules.explore

import au.com.crazybean.mobilex.foundation.saw.Actor
import au.com.crazybean.mobilex.kurir.data.model.User

class ExploreActor(scene: ExploreScene?,
                   wrapper: ExploreWrapper) : Actor<ExploreScene, ExploreWrapper>(scene, wrapper) {
    fun onQuery(query: String?) {
        wrapper.getContacts(query)
            .observe(this) { result ->
                result?.takeIf { it.isNotEmpty() }?.let { users ->
                    scene?.showResult(users)
                }?: scene?.showEmpty()
            }
    }

    fun onContactClick(user: User) {
        scene?.showDetails(user)
        scene?.dismiss()
    }
}