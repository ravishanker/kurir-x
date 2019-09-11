//
//  SignupViewModel.swift
//  Kurir
//
//  Created by Loren on 1/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Crazybean
import Mobilex

class SignupViewModel: ViewModel {
    private let repository: UsersRepository?
    private var optional: User? = nil
    
    var user: User? {
        get {
            return optional
        }
    }
    
    init(_ repository: UsersRepository?) {
        self.repository = repository
    }
    
    func signup(mobile: String, email: String)-> LiveData<Auth?> {
        return LiveData<Auth?> { [weak self] liveData in
            self?.repository?.checkUser(mobile: mobile, email: email, callback: { auth in
                if auth?.result == AuthKt.ERR_NOT_FOUND {
                    self?.optional = User(email: email, mobile: mobile, password: nil, firstName: nil, lastName: nil, userToken: nil)
                }
                
                liveData.value = auth
            })
        }
    }
    
    func register(user: User)-> LiveData<Auth?> {
        return LiveData<Auth?>() { [weak self] liveData in
            if let repository = self?.repository {
                repository.register(user: user, callback: { auth in
                    liveData.value = auth
                })
            }
        }
    }
    
    override func onRelease() {
        repository?.onRelease()
    }
}
