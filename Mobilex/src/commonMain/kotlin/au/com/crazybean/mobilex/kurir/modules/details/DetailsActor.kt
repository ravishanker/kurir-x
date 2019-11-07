package au.com.crazybean.mobilex.kurir.modules.details

import au.com.crazybean.mobilex.foundation.saw.Actor
import au.com.crazybean.mobilex.kurir.data.model.Task
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.fetch

class DetailsActor(scene: DetailsScene?,
                   wrapper: DetailsWrapper) : Actor<DetailsScene, DetailsWrapper>(scene, wrapper) {

    private var task: Task? = null
    private var user: User? = null

    override fun onLoad(params: Map<String, Any?>) {
        super.onLoad(params)

        params.fetch(Extra.USER, User.serializer())?.let {
            user = it
            scene?.showUser(it)
        }?: params.fetch(Extra.TASK, Task.serializer())?.let {
            task = it
            scene?.showTask(it)
        }
    }

    fun onButtonClick() {
        user?.let {
            wrapper.addContact(it).observe(this) { success ->
                    if (success) {
                        scene?.dismiss()
                    } else {
                        scene?.showError()
                    }
                }
        }
    }
}