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
        // Worker
        factory(SignupWorker.self) { r in
            SignupWorker(repository: r.resolve(UsersRepository.self)!)
        }
        
        // Adviser
        factory(SignupAdviser.self) { (r, scene: SignupScene) -> SignupAdviser in
            SignupAdviser(scene: scene, worker: r.resolve(SignupWorker.self)!)
        }
    }
}
