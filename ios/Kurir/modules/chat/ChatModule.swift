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
        factory(ChatWrapper.self) { r in
            ChatWrapper(userData: r.resolve(UserData.self),
                        repository: r.resolve(MessagesRepository.self)!)
        }
        
        // Actor
        factory(ChatActor.self) { (r, scene: ChatScene) -> ChatActor in
            ChatActor(scene: scene, wrapper: r.resolve(ChatWrapper.self)!)
        }
    }
}
