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

class AuthDelegate: Delegate<AuthView, AuthViewModel> {
    override func onCreate(_ params: [String : Any?]? = nil) {
        view?.showLogin(auth: nil)
    }
}
