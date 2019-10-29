package au.com.crazybean.mobilex.kurir.modules.explore

import au.com.crazybean.mobilex.foundation.saw.Adviser
import au.com.crazybean.mobilex.kurir.data.model.User

class ExploreAdviser(scene: ExploreScene?,
                     worker: ExploreWorker) : Adviser<ExploreScene, ExploreWorker>(scene, worker) {
    fun onQuery(query: String?) {
        worker.getContacts(query)
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