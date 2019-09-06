package au.com.crazybean.mobilex.kurir.dependency.global

import au.com.crazybean.mobilex.foundation.userdata.UserData
import au.com.crazybean.mobilex.kurir.impl.PersistUserData
import org.koin.dsl.module

val appModule = module {
    // Storage
    single<UserData> {
        PersistUserData(get())
    }
}