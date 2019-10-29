package au.com.crazybean.mobilex.kurir.dependency

import android.app.Application
import au.com.crazybean.mobilex.kurir.modules.dashboard.dashboardModule
import au.com.crazybean.mobilex.kurir.dependency.domain.domainModule
import au.com.crazybean.mobilex.kurir.dependency.global.appModule
import au.com.crazybean.mobilex.kurir.modules.auth.login.loginModule
import au.com.crazybean.mobilex.kurir.modules.auth.profile.profileModule
import au.com.crazybean.mobilex.kurir.modules.auth.signup.signupModule
import au.com.crazybean.mobilex.kurir.modules.auth.verify.verifyModule
import au.com.crazybean.mobilex.kurir.modules.chat.chatModule
import au.com.crazybean.mobilex.kurir.modules.contacts.contactsModule
import au.com.crazybean.mobilex.kurir.modules.creation.desc.descModule
import au.com.crazybean.mobilex.kurir.modules.creation.creationModule
import au.com.crazybean.mobilex.kurir.modules.creation.receiver.receiverModule
import au.com.crazybean.mobilex.kurir.modules.details.detailsModule
import au.com.crazybean.mobilex.kurir.modules.explore.exploreModule
import au.com.crazybean.mobilex.kurir.modules.tasks.tasksModule
import au.com.crazybean.mobilex.kurir.modules.settings.settingsModule
import au.com.crazybean.mobilex.kurir.modules.track.trackModule
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
                        profileModule,
                        verifyModule,

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