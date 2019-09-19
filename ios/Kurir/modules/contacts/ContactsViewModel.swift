//
//  ContactsViewModel.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Crazybean
import Mobilex

class ContactsViewModel: ViewModel {
    private let userData: UserData?
    private let usersRepository: UsersRepository?
    private let contactsRepository: ContactsRepository?
    
    init(userData: UserData?, usersRepository: UsersRepository?, contactsRepository: ContactsRepository?) {
        self.userData = userData
        self.usersRepository = usersRepository
        self.contactsRepository = contactsRepository
    }
    
    var contacts: LiveData<[User]?> {
        get {
            return LiveData<[User]?>() { [weak self] liveData in
                if let email = self?.userData?.getString(forKey: KeysKt.kEmail, defaultValue: "") {
                    self?.contactsRepository?.getContacts(email: email, completion: { [weak self] emails in
                        if let emails = emails {
                            self?.usersRepository?.getUsers(emails: emails, completion: { users in
                                liveData.value = users
                            })
                        } else {
                            liveData.value = nil
                        }
                    })
                } else {
                    liveData.value = nil
                }
            }
        }
    }
}
