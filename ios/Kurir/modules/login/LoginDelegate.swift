//
//  AuthDelegate.swift
//  Kurir
//
//  Created by Loren on 19/8/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import MobileSDK
import Mobilex

class LoginDelegate: Delegate<LoginView, LoginViewModel> {
    override func onCreate(_ params: [String : Any?]? = nil) {
    }
    
    func onLoginClick(name: String?, password: String?) {
        if let name = name, let password = password {
            viewModel.login(name: name, password: password)
                .observe { [weak self] auth in
                    switch auth?.result {
                    case AuthKt.ERR_NONE:
                        self?.view?.showDashboard()
                        self?.view?.dismiss()
                        
                    case AuthKt.ERR_NOT_FOUND:
                        self?.view?.showNotFound()
                        
                    case AuthKt.ERR_PASSWORD:
                        self?.view?.showPasswordError()
                        
                    default:
                        self?.view?.dismiss()
                    }
            }
        }
    }
    
    func onSignupClick() {
        view?.showRegister()
    }
}
