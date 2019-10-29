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
        // Worker
        factory(VerifyWorker.self) { r in
            VerifyWorker(repository: r.resolve(UsersRepository.self)!)
        }
        
        // Adviser
        factory(VerifyAdviser.self) { (r, scene: VerifyScene) -> VerifyAdviser in
            VerifyAdviser(scene: scene, worker: r.resolve(VerifyWorker.self)!)
        }
    }
}
