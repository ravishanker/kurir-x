//
//  ChatModule.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Crazybean

class ChatModule: Module {
    override func inject() {
        // ViewModel
        factory(ChatViewModel.self) { _ in
            ChatViewModel()
        }
        
        // Delegate
        factory(ChatDelegate.self) { (r, view: ChatView) -> ChatDelegate in
            ChatDelegate(view: view, viewModel: r.resolve(ChatViewModel.self)!)
        }
    }
}
