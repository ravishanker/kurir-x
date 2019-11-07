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
        // Wrapper
        factory(ProfileWrapper.self) { r in
            ProfileWrapper(repository: r.resolve(UsersRepository.self)!)
        }
        
        // Actor
        factory(ProfileActor.self) { (r, scene: ProfileScene) -> ProfileActor in
            ProfileActor(scene: scene, wrapper: r.resolve(ProfileWrapper.self)!)
        }
    }
}
