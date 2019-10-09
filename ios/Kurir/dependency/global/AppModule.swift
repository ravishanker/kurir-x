//
//  AppModule.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Swinject
import Mobilex

class AppModule: Module {
    override func inject() {
        // User Data
        single(UserData.self) { _ in
            UserData()
        }
    }
}
