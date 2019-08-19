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
        register(AuthNetworkDataSource.self) { _ in
            return self.fetch(forType: AuthNetworkDataSource.self) {
                AuthNetworkDataSource()
            }!
        }
        
        // Domain Repository
        register(AuthRepository.self) { r in
            AuthRepository(dataSource: r.resolve(AuthNetworkDataSource.self)!)
        }
    }
}
