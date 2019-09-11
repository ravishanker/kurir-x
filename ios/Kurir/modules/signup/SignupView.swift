//
//  SignupView.swift
//  Kurir
//
//  Created by Loren on 9/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Mobilex

protocol SignupView {
    func showSignup()
    func showLogin()
    func showVerify(user: User?)
    func showExists()
    func showError()
    func showSpinner()
    func hideSpinner()
}
