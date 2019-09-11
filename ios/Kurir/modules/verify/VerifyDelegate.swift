//
//  VerifyDelegate.swift
//  Kurir
//
//  Created by Loren on 9/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Crazybean
import Mobilex

class VerifyDelegate: Delegate<VerifyView, VerifyViewModel> {
    private var user: User? = nil
    
    override func onCreate(_ params: [String : Any?]? = nil) {
        user = fetch()
    }
    
    func onVerifyClick(passcode: String) {
        if (passcode == "123456") {
            view?.showProfile()
        } else {
            view?.showCodeError()
        }
    }
}
