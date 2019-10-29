//
//  ContactsModule.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Mobilex

class ContactsModule: Module {
    override func inject() {
        // Worker
        factory(ContactsWorker.self) { r in
            ContactsWorker(userData: r.resolve(UserData.self),
                           usersRepository: r.resolve(UsersRepository.self),
                           contactsRepository: r.resolve(ContactsRepository.self))
        }
        
        // Adviser
        factory(ContactsAdviser.self) { (r, scene: ContactsScene) -> ContactsAdviser in
            ContactsAdviser(scene: scene, worker: r.resolve(ContactsWorker.self)!)
        }
    }
}
