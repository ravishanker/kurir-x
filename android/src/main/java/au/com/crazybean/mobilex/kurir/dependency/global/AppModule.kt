package au.com.crazybean.mobilex.kurir.dependency.global

import au.com.crazybean.mobilex.sdk.coroutines.customMainScope
import au.com.crazybean.mobilex.kurir.impl.StoragexImpl
import au.com.crazybean.mobilex.sdk.storage.Storagex
import org.koin.dsl.module

val appModule = module {
    // Coroutines Scope.
    single { customMainScope }

    // Storage
    single<Storagex> {
        StoragexImpl(get())
    }
}