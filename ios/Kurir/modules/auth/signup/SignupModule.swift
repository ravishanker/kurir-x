//
//  SignupModule.swift
//  Kurir
//
//  Created by Loren on 1/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Mobilex

class SignupModule: Module {
    override func inject() {
        // Wrapper
        factory(SignupWrapper.self) { r in
            SignupWrapper(repository: r.resolve(UsersRepository.self)!)
        }
        
        // Actor
        factory(SignupActor.self) { (r, scene: SignupScene) -> SignupActor in
            SignupActor(scene: scene, wrapper: r.resolve(SignupWrapper.self)!)
        }
    }
}
