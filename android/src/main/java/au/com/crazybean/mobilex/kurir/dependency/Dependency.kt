package au.com.crazybean.mobilex.kurir.dependency

import android.app.Application
import au.com.crazybean.mobilex.kurir.modules.dashboard.impl.dashboardModule
import au.com.crazybean.mobilex.kurir.dependency.domain.domainModule
import au.com.crazybean.mobilex.kurir.dependency.global.appModule
import au.com.crazybean.mobilex.kurir.modules.auth.login.impl.loginModule
import au.com.crazybean.mobilex.kurir.modules.auth.signup.impl.signupModule
import au.com.crazybean.mobilex.kurir.modules.chat.impl.chatModule
import au.com.crazybean.mobilex.kurir.modules.contacts.impl.contactsModule
import au.com.crazybean.mobilex.kurir.modules.creation.desc.impl.descModule
import au.com.crazybean.mobilex.kurir.modules.creation.impl.creationModule
import au.com.crazybean.mobilex.kurir.modules.creation.receiver.impl.receiverModule
import au.com.crazybean.mobilex.kurir.modules.details.impl.detailsModule
import au.com.crazybean.mobilex.kurir.modules.explore.impl.exploreModule
import au.com.crazybean.mobilex.kurir.modules.tasks.impl.tasksModule
import au.com.crazybean.mobilex.kurir.modules.settings.impl.settingsModule
import au.com.crazybean.mobilex.kurir.modules.track.impl.trackModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Dependency private constructor() {
    companion object {
        fun register(context: Application) {
            // Config Koin
            startKoin {
                androidContext(context)

                // Modules
                modules(
                    listOf(
                        appModule,
                        domainModule,
                        // Auth
                        loginModule,
                        signupModule,

                        // Dashboard
                        dashboardModule,
                        tasksModule,
                        settingsModule,
                        trackModule,

                        // Chat
                        contactsModule,
                        chatModule,

                        // Search
                        exploreModule,
                        detailsModule,

                        // Creation
                        creationModule,
                        descModule,
                        receiverModule
                    )
                )
            }
        }
    }
}