package au.com.crazybean.mobilex.kurir.dependency.domain

import au.com.crazybean.mobilex.kurir.storage.CloudStorage
import au.com.crazybean.mobilex.kurir.impl.FirebaseStorage
import au.com.crazybean.mobilex.kurir.repository.contacts.ContactsRepository
import au.com.crazybean.mobilex.kurir.repository.contacts.ContactsSource
import au.com.crazybean.mobilex.kurir.repository.contacts.cloud.CloudContactsSource
import au.com.crazybean.mobilex.kurir.repository.geo.GeoRepository
import au.com.crazybean.mobilex.kurir.repository.geo.GeoSource
import au.com.crazybean.mobilex.kurir.repository.geo.network.GeoBytesSource
import au.com.crazybean.mobilex.kurir.repository.messages.MessagesRepository
import au.com.crazybean.mobilex.kurir.repository.messages.MessagesSource
import au.com.crazybean.mobilex.kurir.repository.messages.cloud.CloudMessagesSource
import au.com.crazybean.mobilex.kurir.repository.tasks.TasksRepository
import au.com.crazybean.mobilex.kurir.repository.tasks.TasksSource
import au.com.crazybean.mobilex.kurir.repository.tasks.cloud.CloudTasksSource
import au.com.crazybean.mobilex.kurir.repository.users.UsersRepository
import au.com.crazybean.mobilex.kurir.repository.users.UsersSource
import au.com.crazybean.mobilex.kurir.repository.users.cloud.CloudUsersSource
import org.koin.dsl.module

val domainModule = module {
    single<CloudStorage> {
        FirebaseStorage()
    }

    // Users Repository
    single<UsersSource> {
        CloudUsersSource(get())
    }
    single {
        UsersRepository(get())
    }

    // Messages Repository
    single<MessagesSource> {
        CloudMessagesSource(get())
    }
    single {
        MessagesRepository(get())
    }

    // Contacts Repository
    single<ContactsSource> {
        CloudContactsSource(get())
    }
    single {
        ContactsRepository(get())
    }

    // Tasks Repository
    single<TasksSource> {
        CloudTasksSource(get())
    }
    single {
        TasksRepository(get())
    }

    // Geo Repository
    single<GeoSource> {
        GeoBytesSource()
    }
    single {
        GeoRepository(get())
    }
}