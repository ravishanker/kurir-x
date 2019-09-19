//
//  ExploreViewModel.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Crazybean
import Mobilex

class ExploreViewModel: ViewModel {
    private let repository: UsersRepository?
    
    init(repository: UsersRepository?) {
        self.repository = repository
    }
}

