//
//  ProfileDelegate.swift
//  Kurir
//
//  Created by Loren on 10/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Crazybean
import Mobilex

class ProfileDelegate: Delegate<ProfileView, ProfileViewModel> {
    private var enroll: Enroll? = nil
    
    override func onCreate(_ params: [String : Any?]? = nil) {
        enroll = fetch()
        view?.hideButton()
    }
    
    func onPasswordType(password: String?, confirm: String?) {
        if let password = password, let confirm = confirm {
            if password == confirm {
                view?.showButton()
            } else {
                view?.hideButton()
            }
        } else {
            view?.hideButton()
        }
    }
    
    func onRegister(firstName: String?, lastName: String?, password: String?) {
        if let enroll = enroll {
            let newUser = User(email: enroll.email, mobile: enroll.mobile, password: password, firstName: firstName, lastName: lastName)
            view?.showSpinner()
            viewModel.register(user: newUser).observe { [weak self] auth in
                self?.view?.hideSpinner()
                
                // Check the result
                if auth?.result == AuthKt.ERR_NONE {
                    self?.view?.showDashboard()
                } else {
                    self?.view?.showError()
                }
            }
        }
    }
}
