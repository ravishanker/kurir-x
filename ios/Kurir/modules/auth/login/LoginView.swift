//
//  LoginView.swift
//  Kurir
//
//  Created by Loren on 9/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Mobilex

protocol LoginView {
    func showError(auth: Auth?)
    func showNameError()
    func showPasswordError()
    func showDashboard()
    func showNotFound()
    func dismiss()
    func showSpinner()
    func hideSpinner()
}
