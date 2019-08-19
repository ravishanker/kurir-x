package au.com.crazybean.mobilex.kurir.dependency.domain

import au.com.crazybean.mobilex.repository.auth.AuthRepository
import au.com.crazybean.mobilex.repository.auth.network.AuthNetworkDataSource
import org.koin.dsl.module

val domainModule = module {
    single { AuthNetworkDataSource() }

    single { AuthRepository(get()) }
}