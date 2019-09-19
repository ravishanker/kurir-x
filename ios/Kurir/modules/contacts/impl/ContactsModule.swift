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
        // ViewModel
        factory(ContactsViewModel.self) { r in
            ContactsViewModel(userData: r.resolve(UserData.self),
                              usersRepository: r.resolve(UsersRepository.self),
                              contactsRepository: r.resolve(ContactsRepository.self))
        }
        
        // Delegate
        factory(ContactsDelegate.self) { (r, view: ContactsView) -> ContactsDelegate in
            ContactsDelegate(view: view, viewModel: r.resolve(ContactsViewModel.self)!)
        }
    }
}
