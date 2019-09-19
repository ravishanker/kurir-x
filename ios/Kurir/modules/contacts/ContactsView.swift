//
//  ContactsView.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Mobilex

protocol ContactsView {
    func showContacts(users: [User])
    func showChat(user: User)
    func showExplore()
    func showEmpty()
}
