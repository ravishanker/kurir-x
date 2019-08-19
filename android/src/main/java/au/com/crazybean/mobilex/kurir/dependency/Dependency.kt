package au.com.crazybean.mobilex.kurir.dependency

import android.app.Application
import au.com.crazybean.mobilex.kurir.dependency.auth.authModule
import au.com.crazybean.mobilex.kurir.dependency.domain.domainModule
import au.com.crazybean.mobilex.kurir.dependency.global.appModule
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
                        authModule))
            }
        }
    }
}