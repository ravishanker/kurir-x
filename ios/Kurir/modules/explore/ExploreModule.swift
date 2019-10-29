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
        // Worker
        factory(ExploreWorker.self) { r in
            ExploreWorker(repository: r.resolve(UsersRepository.self))
        }
        
        // Adviser
        factory(ExploreAdviser.self) { (r, scene: ExploreScene) -> ExploreAdviser in
            ExploreAdviser(scene: scene, worker: r.resolve(ExploreWorker.self)!)
        }
    }
}
