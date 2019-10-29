//
//  VerifyViewController.swift
//  Kurir
//
//  Created by Loren on 8/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import UIKit
import Mobilex

class VerifyViewController: BaseViewController<VerifyAdviser, VerifyScene>, VerifyScene {
    @IBOutlet weak var passcodeField: UITextField!
    
    func showProfile(user: User?) {
        performSegue(withIdentifier: "profileView", sender: self, object: user)
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
        adviser?.onVerifyClick(passcode: passcodeField?.text ?? "")
    }
}
