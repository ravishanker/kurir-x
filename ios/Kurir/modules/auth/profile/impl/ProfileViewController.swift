//
//  ProfileViewController.swift
//  Kurir
//
//  Created by Loren on 10/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Crazybean
import Mobilex

class ProfileViewController: AppViewController, ProfileView {
    private(set) lazy var delegate = UIViewController.resolve(type: ProfileDelegate.self, argument: self as ProfileView)
    
    @IBOutlet weak var firstNameField: UITextField!
    @IBOutlet weak var lastNameField: UITextField!
    @IBOutlet weak var passwordField: UITextField!
    @IBOutlet weak var confirmField: UITextField!
    @IBOutlet weak var registerButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        delegate?.authorise(self, params: params)
    }
    
    func showDashboard() {
    }
    
    func showNameError() {
    }
    
    func showPasswordError() {
    }
    
    func showError() {
        showError("ERROR_GENERIC")
    }
    
    func showButton() {
        registerButton?.isHidden = false
    }
    
    func hideButton() {
        registerButton?.isHidden = true
    }
    
    func showSpinner() {
        isIndicatorHidden = false
    }
    
    func hideSpinner() {
        isIndicatorHidden = true
    }
    
    @IBAction func onRegisterTap(_ sender: Any) {
        let firstName = firstNameField.text
        let lastName = lastNameField.text
        let password = passwordField.text
        
        delegate?.onRegister(firstName: firstName, lastName: lastName, password: password)
    }
    
    @IBAction func onPasswordEditChanged(_ sender: Any) {
        let password = passwordField.text
        let confirm = confirmField.text
        
        delegate?.onPasswordType(password: password, confirm: confirm)
    }
}
