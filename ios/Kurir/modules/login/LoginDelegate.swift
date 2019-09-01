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
                    // Check the result
                    if auth?.result != AuthKt.ERR_NONE {
                        self?.view?.showError(auth: auth)
                    } else {
                        self?.view?.showDashboard()
                    }
            }
        }
    }
    
    func onSignupClick() {
        view?.showRegister()
    }
}
