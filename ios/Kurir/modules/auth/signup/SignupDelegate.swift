//
//  SignupDelegate.swift
//  Kurir
//
//  Created by Loren on 1/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Crazybean
import Mobilex

class SignupDelegate: Delegate<SignupView, SignupViewModel> {
    // Switch back to login
    func onLoginClick() {
        view?.showLogin()
    }
    
    func onRegister(mobile: String, email: String) {
        view?.showSpinner()
        viewModel.signup(mobile: mobile, email: email).observe { [weak self] auth in
            self?.view?.hideSpinner()
            
            switch auth?.result {
            case AuthKt.ERR_NOT_FOUND:
                self?.view?.showVerify(user: self?.viewModel.user)
                
            case AuthKt.ERR_EXISTS:
                self?.view?.showExists()
                
            default:
                self?.view?.showError()
            }
        }
    }
    
    override func onStop() {
        viewModel.onRelease()
    }
}
