//
//  VerifyModule.swift
//  Kurir
//
//  Created by Loren on 9/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Mobilex

class VerifyModule: Module {
    override func inject() {
        // ViewModel
        factory(VerifyViewModel.self) { r in
            VerifyViewModel(r.resolve(UsersRepository.self)!)
        }
        
        // Delegate
        factory(VerifyDelegate.self) { (r, view: VerifyView) -> VerifyDelegate in
            VerifyDelegate(view: view, viewModel: r.resolve(VerifyViewModel.self)!)
        }
    }
}
