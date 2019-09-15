package au.com.crazybean.mobilex.kurir.dependency.domain

import au.com.crazybean.mobilex.kurir.storage.CloudStorage
import au.com.crazybean.mobilex.kurir.impl.FirebaseStorage
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository
import au.com.crazybean.mobilex.kurir.repository.users.UsersSource
import au.com.crazybean.mobilex.kurir.repository.users.cloud.CloudUsersSource
import org.koin.dsl.module

val domainModule = module {
    single<CloudStorage> {
        FirebaseStorage()
    }

    // Users Repository
    single<UsersSource> { CloudUsersSource(get()) }
    single { UsersRepository(get()) }
}