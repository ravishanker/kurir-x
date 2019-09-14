//
//  SignupViewController.swift
//  Kurir
//
//  Created by Loren on 1/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Crazybean
import Mobilex

class SignupViewController: AppViewController, SignupView {
    @IBOutlet weak var mobileField: UITextField!
    @IBOutlet weak var emailField: UITextField!
    
    private(set) lazy var delegate = UIViewController.resolve(type: SignupDelegate.self, argument: self as SignupView)
    
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
        delegate?.onLoginClick()
    }
    
    @IBAction func onCancelTap(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
    
    @IBAction func onSignupTap(_ sender: Any) {
        delegate?.onRegister(mobile: mobileField?.text ?? "", email: emailField?.text ?? "")
    }
}
