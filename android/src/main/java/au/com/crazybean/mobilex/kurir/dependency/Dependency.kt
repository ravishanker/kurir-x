package au.com.crazybean.mobilex.kurir.dependency

import android.app.Application
import au.com.crazybean.mobilex.kurir.dependency.domain.domainModule
import au.com.crazybean.mobilex.kurir.dependency.global.appModule
import au.com.crazybean.mobilex.kurir.dependency.login.loginModule
import au.com.crazybean.mobilex.kurir.dependency.signup.signupModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Dependency private constructor() {
    companion object {
        fun register(context: Application) {
            // Config Koin
            startKoin {
                androidContext(context)

                // Modules
                modules(listOf(appModule,
                        domainModule,
                        loginModule,
                        signupModule))
            }
        }
    }
}