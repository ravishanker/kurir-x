//
//  AuthViewModel.swift
//  Kurir
//
//  Created by Loren on 19/8/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import MobileSDK
import Mobilex

class LoginViewModel: ViewModel {
    private let repository: AuthRepository?
    
    init(_ repository: AuthRepository?) {
        self.repository = repository
    }
    
    func login(name: String, password: String)-> LiveData<Auth?> {
        return LiveData<Auth?>() { [weak self] liveData in
            if let repository = self?.repository {
                repository.login(name: name, password: password, callback: { auth in
                    liveData.value = auth
                })
            }
        }
    }
}
