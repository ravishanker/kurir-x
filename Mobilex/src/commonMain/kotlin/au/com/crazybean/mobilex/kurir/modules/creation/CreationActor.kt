package au.com.crazybean.mobilex.kurir.modules.creation

import au.com.crazybean.mobilex.foundation.saw.Actor

class CreationActor(scene: CreationScene?,
                    wrapper: CreationWrapper) : Actor<CreationScene, CreationWrapper>(scene, wrapper) {
    override fun onLoad(params: Map<String, Any?>) {
        super.onLoad(params)
        scene?.showCreation()
    }
}