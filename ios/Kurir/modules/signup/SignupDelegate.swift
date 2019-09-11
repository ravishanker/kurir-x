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
            if let this = self {
                this.view?.hideSpinner()
                this.view?.showVerify(user: this.viewModel.user)
            }
        }
    }
    
    override func onStop() {
        viewModel.onRelease()
    }
}
