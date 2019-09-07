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
        register(UsersNetworkSource.self) { _ in
            return self.fetch(forType: UsersNetworkSource.self) {
                UsersNetworkSource()
            }!
        }
        
        register(UsersSource.self) { r in
            return self.fetch(forType: UsersSource.self) {
                UsersDatabase(database: DatabaseImpl())
                }!
        }
        
        // Domain Repository
        register(UsersRepository.self) { r in
            UsersRepository(dbSource: r.resolve(UsersDatabase.self), networkSource: nil)
        }
    }
}
