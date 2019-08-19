//
//  AuthModule.swift
//  Kurir
//
//  Created by Loren on 19/8/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Mobilex

class AuthModule: Module {
    override func inject() {
        // Wrapper
        register(AuthWrapper.self) { r in
            AuthWrapper(repository: r.resolve(AuthRepository.self)!)
        }
        
        // ViewModel
        register(AuthViewModel.self) { r in
            AuthViewModel(r.resolve(AuthWrapper.self)!)
        }
        
        // Delegate
        register(AuthDelegate.self) { (r, view: AuthView) -> AuthDelegate in
            AuthDelegate(view: view, viewModel: r.resolve(AuthViewModel.self)!)
        }
    }
}
