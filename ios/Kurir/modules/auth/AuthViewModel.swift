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

class AuthViewModel: ViewModel {
    private let wrapper: AuthWrapper?
    
    init(_ wrapper: AuthWrapper?) {
        self.wrapper = wrapper
    }
    
    func login(name: String, password: String)-> LiveData<Auth?> {
        return LiveData<Auth?>() { [weak self] liveData in
            if let wrapper = self?.wrapper {
                wrapper.login(name: name, password: password, completion: { auth in
                    liveData.value = auth
                })
            }
        }
    }
}
