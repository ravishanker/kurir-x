package au.com.crazybean.mobilex.kurir.dependency.domain

import au.com.crazybean.mobilex.database.Database
import au.com.crazybean.mobilex.kurir.impl.DatabaseImpl
import au.com.crazybean.mobilex.kurir.repository.auth.AuthRepository
import au.com.crazybean.mobilex.kurir.repository.auth.database.UsersDatabase
import org.koin.dsl.module

val domainModule = module {
    single<Database> {
        DatabaseImpl()
    }

    single { UsersDatabase(get()) }

    single { AuthRepository(get()) }
}