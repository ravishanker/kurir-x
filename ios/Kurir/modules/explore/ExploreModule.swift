//
//  ExploreModule.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Mobilex

class ExploreModule: Module {
    override func inject() {
        // Wrapper
        factory(ExploreWrapper.self) { r in
            ExploreWrapper(repository: r.resolve(UsersRepository.self))
        }
        
        // Actor
        factory(ExploreActor.self) { (r, scene: ExploreScene) -> ExploreActor in
            ExploreActor(scene: scene, wrapper: r.resolve(ExploreWrapper.self)!)
        }
    }
}
