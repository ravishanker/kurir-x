package au.com.crazybean.mobilex.kurir.modules.auth.profile

import au.com.crazybean.mobilex.foundation.saw.Actor
import au.com.crazybean.mobilex.kurir.data.model.ERR_NONE
import au.com.crazybean.mobilex.kurir.data.model.Enroll
import au.com.crazybean.mobilex.kurir.data.model.User
import au.com.crazybean.mobilex.kurir.modules.base.Extra
import au.com.crazybean.mobilex.kurir.modules.base.fetch

class ProfileActor(scene: ProfileScene?,
                   wrapper: ProfileWrapper) : Actor<ProfileScene, ProfileWrapper>(scene, wrapper) {
    private var enroll: Enroll? = null

    override fun onLoad(params: Map<String, Any?>) {
        super.onLoad(params)
        enroll = params.fetch(Extra.ENROLL, Enroll.serializer())
        scene?.hideButton()
    }

    fun onPasswordType(password: String?, confirm: String?) {
        if (password.isNullOrBlank() || confirm.isNullOrBlank()) {
            scene?.hideButton()
        } else if (password == confirm) {
            scene?.showButton()
        } else {
            scene?.hideButton()
        }
    }

    fun onRegister(firstName: String?, lastName: String?, password: String?) {
        enroll?.let {
            val newUser = User(it.email, it.mobile, password, firstName, lastName)
            scene?.showSpinner()

            wrapper.register(newUser).observe(this) { auth ->
                scene?.hideSpinner()

                // Check the result
                if (auth?.result == ERR_NONE) {
                    scene?.showDashboard()
                } else {
                    scene?.showError()
                }
            }
        }
    }
}