//
//  ExploreModule.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Mobilex

class ExploreModule: Module {
    override func inject() {
        // ViewModel
        factory(ExploreViewModel.self) { r in
            ExploreViewModel(repository: r.resolve(UsersRepository.self))
        }
        
        // Delegate
        factory(ExploreDelegate.self) { (r, view: ExploreView) -> ExploreDelegate in
            ExploreDelegate(view: view, viewModel: r.resolve(ExploreViewModel.self)!)
        }
    }
}
