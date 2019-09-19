//
//  FindModule.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Mobilex

class FindModule: Module {
    override func inject() {
        // ViewModel
        factory(FindViewModel.self) { _ in
            FindViewModel()
        }
        
        // Delegate
        factory(FindDelegate.self) { (r, view: FindView) -> FindDelegate in
            FindDelegate(view: view, viewModel: r.resolve(FindViewModel.self)!)
        }
    }
}
