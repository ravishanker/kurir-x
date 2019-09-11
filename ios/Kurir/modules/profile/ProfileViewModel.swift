//
//  ProfileViewModel.swift
//  Kurir
//
//  Created by Loren on 10/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Crazybean
import Mobilex

class ProfileViewModel: ViewModel {
    private let repository: UsersRepository?
    
    init(_ repository: UsersRepository?) {
        self.repository = repository
    }
}
