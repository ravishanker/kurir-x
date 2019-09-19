//
//  ContactsDelegate.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Crazybean
import Mobilex

class ContactsDelegate: Delegate<ContactsView, ContactsViewModel> {
    override func onResume() {
        super.onResume()
        viewModel.contacts.observe { [weak self] users in
            if let users = users {
                self?.view?.showContacts(users: users)
            } else {
                self?.view?.showEmpty()
            }
        }
    }
    
    func onAddClick() {
        view?.showExplore()
    }
    
    func onContactClick(user: User) {
        view?.showChat(user: user)
    }
}
