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
        // Wrapper
        factory(LoginWrapper.self) { r in
            LoginWrapper(userData: r.resolve(UserData.self), repository: r.resolve(UsersRepository.self)!)
        }
        
        // Actor
        factory(LoginActor.self) { (r, scene: LoginScene) -> LoginActor in
            LoginActor(scene: scene, wrapper: r.resolve(LoginWrapper.self)!)
        }
    }
}
