//
//  DomainModule.swift
//  Kurir
//
//  Created by Loren on 19/8/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Swinject
import Mobilex

class DomainModule: Module {
    override func inject() {
        // Cloud storage
        single(CloudStorage.self) { _ in
            FirebaseStorage()
        }
        
        // Users Repository
        single(UsersSource.self) { r in
            CloudUsersSource(storage: r.resolve(CloudStorage.self)!)
        }
        
        single(UsersRepository.self) { r in
            UsersRepository(usersSource: r.resolve(UsersSource.self))
        }
        
        // Contacts Repository
        single(ContactsSource.self) { r in
            CloudContactsSource(storage: r.resolve(CloudStorage.self)!)
        }
        
        single(ContactsRepository.self) { r in
            ContactsRepository(contactsSource: r.resolve(ContactsSource.self)!)
        }
        
        // Messages Repository
        single(MessagesSource.self) { r in
            CloudMessagesSource(storage: r.resolve(CloudStorage.self)!)
        }
        
        single(MessagesRepository.self) { r in
            MessagesRepository(messagesSource: r.resolve(MessagesSource.self)!)
        }
    }
}
