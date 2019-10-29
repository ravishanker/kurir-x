//
//  ProfileModule.swift
//  Kurir
//
//  Created by Loren on 10/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Mobilex

class ProfileModule: Module {
    override func inject() {
        // Worker
        factory(ProfileWorker.self) { r in
            ProfileWorker(repository: r.resolve(UsersRepository.self)!)
        }
        
        // Adviser
        factory(ProfileAdviser.self) { (r, scene: ProfileScene) -> ProfileAdviser in
            ProfileAdviser(scene: scene, worker: r.resolve(ProfileWorker.self)!)
        }
    }
}
