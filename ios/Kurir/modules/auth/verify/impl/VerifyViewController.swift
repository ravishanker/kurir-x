//
//  VerifyViewController.swift
//  Kurir
//
//  Created by Loren on 8/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Crazybean
import Mobilex

class VerifyViewController: BaseViewController<VerifyView, VerifyViewModel, VerifyDelegate>, VerifyView {
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
        delegate?.onVerifyClick(passcode: passcodeField?.text ?? "")
    }
}