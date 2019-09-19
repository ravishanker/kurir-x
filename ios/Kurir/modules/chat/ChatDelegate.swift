//
//  ChatDelegate.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Crazybean
import Mobilex

class ChatDelegate: Delegate<ChatView, ChatViewModel> {
    private var user: User? = nil
    
    override func onCreate(_ params: [String : Any?]? = nil) {
        super.onCreate(params)
        user = fetch()
    }
}
