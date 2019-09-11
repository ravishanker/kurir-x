//
//  VerifyViewController.swift
//  Kurir
//
//  Created by Loren on 8/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Crazybean

class VerifyViewController: AppViewController, VerifyView {
    @IBOutlet weak var passcodeField: UITextField!
    
    private(set) lazy var delegate = UIViewController.resolve(type: VerifyDelegate.self, argument: self as VerifyView)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        delegate?.authorise(self, params: params)
    }
    
    func showProfile() {
        performSegue(withIdentifier: "profileView", sender: self)
    }
    
    func showCodeError() {
        showError("ERROR_WRONG_CODE")
    }
    
    func showSpinner() {
        isIndicatorHidden = false
    }
    
    func hideSpinner() {
        isIndicatorHidden = true
    }
    
    @IBAction func onVerifyTap(_ sender: Any) {
        delegate?.onVerifyClick(passcode: passcodeField?.text ?? "")
    }
}
