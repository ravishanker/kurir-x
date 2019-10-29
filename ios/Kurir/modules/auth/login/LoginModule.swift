//
//  LoginModule.swift
//  Kurir
//
//  Created by Loren on 19/8/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Mobilex

class LoginModule: Module {
    override func inject() {
        // Worker
        factory(LoginWorker.self) { r in
            LoginWorker(userData: r.resolve(UserData.self), repository: r.resolve(UsersRepository.self)!)
        }
        
        // Adviser
        factory(LoginAdviser.self) { (r, scene: LoginScene) -> LoginAdviser in
            LoginAdviser(scene: scene, worker: r.resolve(LoginWorker.self)!)
        }
    }
}
