//
//  LoginModule.swift
//  Kurir
//
//  Created by Loren on 19/8/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Mobilex

class LoginModule: Module {
    override func inject() {
        // ViewModel
        factory(LoginViewModel.self) { r in
            LoginViewModel(userData: r.resolve(UserData.self), repository: r.resolve(UsersRepository.self)!)
        }
        
        // Delegate
        factory(LoginDelegate.self) { (r, view: LoginView) -> LoginDelegate in
            LoginDelegate(view: view, viewModel: r.resolve(LoginViewModel.self)!)
        }
    }
}
