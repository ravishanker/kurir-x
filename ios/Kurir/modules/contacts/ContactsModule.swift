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
        // Wrapper
        factory(ContactsWrapper.self) { r in
            ContactsWrapper(userData: r.resolve(UserData.self),
                            usersRepository: r.resolve(UsersRepository.self),
                            contactsRepository: r.resolve(ContactsRepository.self))
        }
        
        // Actor
        factory(ContactsActor.self) { (r, scene: ContactsScene) -> ContactsActor in
            ContactsActor(scene: scene, wrapper: r.resolve(ContactsWrapper.self)!)
        }
    }
}
