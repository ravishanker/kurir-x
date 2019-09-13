//
//  ProfileView.swift
//  Kurir
//
//  Created by Loren on 10/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation

protocol ProfileView {
    func showDashboard()
    func showError()
    func showNameError()
    func showPasswordError()
    func showButton()
    func hideButton()
    func showSpinner()
    func hideSpinner()
}
