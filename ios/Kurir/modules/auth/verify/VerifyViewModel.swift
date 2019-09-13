//
//  VerifyViewModel.swift
//  Kurir
//
//  Created by Loren on 9/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Crazybean
import Mobilex

class VerifyViewModel: ViewModel {
    private let repository: UsersRepository?
    
    init(_ repository: UsersRepository?) {
        self.repository = repository
    }
}
