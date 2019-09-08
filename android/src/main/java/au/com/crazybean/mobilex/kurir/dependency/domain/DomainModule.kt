package au.com.crazybean.mobilex.kurir.dependency.domain

import au.com.crazybean.mobilex.kurir.database.Database
import au.com.crazybean.mobilex.kurir.impl.DatabaseImpl
import au.com.crazybean.mobilex.kurir.repository.tasks.TasksRepository
import au.com.crazybean.mobilex.kurir.repository.tasks.TasksSource
import au.com.crazybean.mobilex.kurir.repository.tasks.database.TasksDatabase
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository
import au.com.crazybean.mobilex.kurir.repository.users.UsersSource
import au.com.crazybean.mobilex.kurir.repository.users.database.UsersDatabase
import org.koin.dsl.module

val domainModule = module {
    single<Database> {
        DatabaseImpl()
    }

    // Users Repository
    single<UsersSource> { UsersDatabase(get()) }
    single { UsersRepository(get()) }

    // Tasks Repository
    single<TasksSource> { TasksDatabase(get()) }
    single { TasksRepository(get()) }
}