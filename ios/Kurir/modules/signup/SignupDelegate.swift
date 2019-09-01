//
//  SignupDelegate.swift
//  Kurir
//
//  Created by Loren on 1/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import MobileSDK
import Mobilex

class SignupDelegate: Delegate<SignupView, SignupViewModel> {
    
    // Switch back to login
    func onLoginClick() {
        view?.showLogin()
    }
}
