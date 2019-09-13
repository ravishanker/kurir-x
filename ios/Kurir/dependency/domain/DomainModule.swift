//
//  DomainModule.swift
//  Kurir
//
//  Created by Loren on 19/8/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Swinject
import Mobilex

class DomainModule: Module {
    override func inject() {
        single(UsersSource.self) { r in
            UsersDatabase(database: DatabaseImpl())
        }
        
        // Domain Repository
        single(UsersRepository.self) { r in
            UsersRepository(usersSource: r.resolve(UsersSource.self))
        }
    }
}
