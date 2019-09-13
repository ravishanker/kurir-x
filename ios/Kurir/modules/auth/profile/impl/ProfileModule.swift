//
//  ProfileModule.swift
//  Kurir
//
//  Created by Loren on 10/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Mobilex

class ProfileModule: Module {
    override func inject() {
        // ViewModel
        factory(ProfileViewModel.self) { r in
            ProfileViewModel(r.resolve(UsersRepository.self)!)
        }
        
        // Delegate
        factory(ProfileDelegate.self) { (r, view: ProfileView) -> ProfileDelegate in
            ProfileDelegate(view: view, viewModel: r.resolve(ProfileViewModel.self)!)
        }
    }
}
