//
//  SignupViewController.swift
//  Kurir
//
//  Created by Loren on 1/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import MobileSDK
import Mobilex

class SignupViewController: UICompatViewController, SignupView {
    private(set) lazy var delegate = UIViewController.resolve(type: SignupDelegate.self, argument: self as SignupView)
    
    func showSignup() {
    }
    
    func showLogin() {
    }
    
    func showVerify() {
    }
    
    func showProfile() {
    }
    
    func showCodeError() {
    }
    
    func showExists() {
    }
    
    func showError() {
    }
    
    func showDashboard() {
    }
    
    func dismiss() {
    }
}
