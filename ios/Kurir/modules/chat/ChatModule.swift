//
//  ChatModule.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Mobilex

class ChatModule: Module {
    override func inject() {
        // Worker
        factory(ChatWorker.self) { r in
            ChatWorker(userData: r.resolve(UserData.self),
                       repository: r.resolve(MessagesRepository.self)!)
        }
        
        // Adviser
        factory(ChatAdviser.self) { (r, scene: ChatScene) -> ChatAdviser in
            ChatAdviser(scene: scene, worker: r.resolve(ChatWorker.self)!)
        }
    }
}
