//
//  SignupViewController.swift
//  Kurir
//
//  Created by Loren on 1/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import UIKit
import Mobilex

class SignupViewController: BaseViewController<SignupAdviser, SignupScene>, SignupScene {
    @IBOutlet weak var mobileField: UITextField!
    @IBOutlet weak var emailField: UITextField!
    
    func showSignup() {
    }
    
    func showLogin() {
        dismiss(animated: true, completion: nil)
    }
    
    func showVerify(enroll: Enroll?) {
        performSegue(withIdentifier: "verifyView", sender: self, object: enroll)
    }
    
    func showExists() {
        showError("ERROR_USER_EXISTS")
    }
    
    func showError() {
        showError("ERROR_GENERIC")
    }
    
    func showSpinner() {
        isIndicatorHidden = false
    }
    
    func hideSpinner() {
        isIndicatorHidden = true
    }
    
    @IBAction func onLoginTap(_ sender: Any) {
        adviser?.onLoginClick()
    }
    
    @IBAction func onCancelTap(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
    
    @IBAction func onSignupTap(_ sender: Any) {
        adviser?.onRegister(mobile: mobileField?.text ?? "", email: emailField?.text ?? "")
    }
}
