//
//  AuthViewModel.swift
//  Kurir
//
//  Created by Loren on 19/8/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Crazybean
import Mobilex

class LoginViewModel: ViewModel {
    private let userData: UserData?
    private let repository: UsersRepository?
    
    init(userData: UserData?, repository: UsersRepository?) {
        self.userData = userData
        self.repository = repository
    }
    
    func login(name: String, password: String)-> LiveData<Auth?> {
        return LiveData<Auth?>() { [weak self] liveData in
            if let repository = self?.repository {
                repository.login(name: name, password: password, completion: { auth in
                    liveData.value = auth
                    
                    if let user = auth?.user {
                        // Save the login user name and password onto user data.
                        self?.userData?.setString(value: user.email ?? "", forKey: KeysKt.kEmail)
                        self?.userData?.setString(value: user.password ?? "", forKey: KeysKt.kPassword)
                    }
                })
            }
        }
    }
}
