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
    
    func showVerify(user: User?) {
        performSegue(withIdentifier: "verifyView", sender: self, object: user)
    }
    
    func showExists() {
    }
    
    func showError() {
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
