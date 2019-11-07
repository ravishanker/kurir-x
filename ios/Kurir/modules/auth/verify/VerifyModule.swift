//
//  VerifyModule.swift
//  Kurir
//
//  Created by Loren on 9/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Mobilex

class VerifyModule: Module {
    override func inject() {
        // Wrapper
        factory(VerifyWrapper.self) { r in
            VerifyWrapper(repository: r.resolve(UsersRepository.self)!)
        }
        
        // Actor
        factory(VerifyActor.self) { (r, scene: VerifyScene) -> VerifyActor in
            VerifyActor(scene: scene, wrapper: r.resolve(VerifyWrapper.self)!)
        }
    }
}
