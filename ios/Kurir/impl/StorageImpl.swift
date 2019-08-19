//
//  StorageImpl.swift
//  mobilex
//
//  Created by Loren on 16/8/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Mobilex

class StorageImpl: Storagex {
    private lazy var userDefaults = UserDefaults.standard
    
    func setInt(value: Int32, forKey: String) {
        userDefaults.set(value, forKey: forKey)
        userDefaults.synchronize()
    }
    
    func setLong(value: Int64, forKey: String) {
        userDefaults.set(value, forKey: forKey)
        userDefaults.synchronize()
    }
    
    func setBool(value: Bool, forKey: String) {
        userDefaults.set(value, forKey: forKey)
        userDefaults.synchronize()
    }
    
    func setString(value: String, forKey: String) {
        userDefaults.set(value, forKey: forKey)
        userDefaults.synchronize()
    }
    
    func getInt(forKey: String, defaultValue: Int32) -> Int32 {
        if let _ = userDefaults.object(forKey: forKey) {
            return Int32(userDefaults.integer(forKey: forKey))
        }
        
        return defaultValue
    }
    
    func getLong(forKey: String, defaultValue: Int64) -> Int64 {
        if let _ = userDefaults.object(forKey: forKey) {
            return Int64(userDefaults.integer(forKey: forKey))
        }
        
        return defaultValue
    }
    
    func getBool(forKey: String, defaultValue: Bool) -> Bool {
        if let _ = userDefaults.object(forKey: forKey) {
            return userDefaults.bool(forKey: forKey)
        }
        
        return defaultValue
    }
    
    func getString(forKey: String, defaultValue: String?) -> String? {
        if let _ = userDefaults.object(forKey: forKey) {
            return userDefaults.string(forKey: forKey)
        }
        
        return defaultValue
    }
    
    func delete(forKey: String) -> Storagex {
        userDefaults.removeObject(forKey: forKey)
        userDefaults.synchronize()
        return self
    }
    
    func exists(key: String) -> Bool {
        return userDefaults.object(forKey: key) != nil
    }
    
    func clear() {
        userDefaults.removePersistentDomain(forName: UserDefaults.registrationDomain)
        userDefaults.synchronize()
    }
    
}
