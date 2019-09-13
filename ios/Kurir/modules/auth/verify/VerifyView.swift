//
//  VerifyView.swift
//  Kurir
//
//  Created by Loren on 9/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Mobilex

protocol VerifyView {
    func showCodeError()
    func showProfile(user: User?)
    func showSpinner()
    func hideSpinner()
}
