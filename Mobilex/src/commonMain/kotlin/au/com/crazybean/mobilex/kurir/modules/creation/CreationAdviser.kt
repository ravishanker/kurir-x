package au.com.crazybean.mobilex.kurir.modules.creation

import au.com.crazybean.mobilex.foundation.saw.Adviser

class CreationAdviser(scene: CreationScene?,
                      worker: CreationWorker) : Adviser<CreationScene, CreationWorker>(scene, worker) {
    override fun onLoad(params: Map<String, Any?>) {
        super.onLoad(params)
        scene?.showCreation()
    }
}