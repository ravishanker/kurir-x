//
//  SignupModule.swift
//  Kurir
//
//  Created by Loren on 1/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Mobilex

class SignupModule: Module {
    override func inject() {
        // ViewModel
        register(SignupViewModel.self) { r in
            SignupViewModel(r.resolve(UsersRepository.self)!)
        }
        
        // Delegate
        register(SignupDelegate.self) { (r, view: SignupView) -> SignupDelegate in
            SignupDelegate(view: view, viewModel: r.resolve(SignupViewModel.self)!)
        }
    }
}
