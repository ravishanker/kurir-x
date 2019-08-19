//
//  DefaultsStorage.swift
//  Mobile SDK
//
//  Created by Crazybean Studio on 20/02/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation

public class DefaultsStorage: Storage {
    private lazy var userDefaults = UserDefaults.standard
    
    public init() {
    }
    
    public func set(_ value: Int, forKey key: String) {
        userDefaults.set(value, forKey: key)
        userDefaults.synchronize()
    }
    
    public func set(_ value: Bool, forKey key: String) {
        userDefaults.set(value, forKey: key)
        userDefaults.synchronize()
    }
  
    public func set(_ value: Float, forKey key: String) {
      userDefaults.set(value, forKey: key)
      userDefaults.synchronize()
    }
  
    public func set(_ value: Double, forKey key: String) {
      userDefaults.set(value, forKey: key)
      userDefaults.synchronize()
    }
    
    public func set(_ value: String?, forKey key: String) {
        userDefaults.set(value, forKey: key)
        userDefaults.synchronize()
    }
  
    public func set(_ value: Any?, forKey key: String) {
        userDefaults.set(value, forKey: key)
        userDefaults.synchronize()
    }
    
    public func object(forKey key: String) -> Any? {
        return userDefaults.object(forKey: key)
    }
    
    public func objects(forKey key: String) -> [Any]? {
        return userDefaults.array(forKey: key)
    }
    
    public func strings(forKey key: String) -> [String]? {
        return userDefaults.stringArray(forKey: key)
    }
    
    public func integer(forKey key: String) -> Int {
        return userDefaults.integer(forKey: key)
    }
    
    public func bool(forKey key: String) -> Bool {
        return userDefaults.bool(forKey: key)
    }
    
    public func float(forKey key: String) -> Float {
        return userDefaults.float(forKey: key)
    }
    
    public func double(forKey key: String) -> Double {
        return userDefaults.double(forKey: key)
    }
    
    public func string(forKey key: String) -> String? {
        return userDefaults.string(forKey: key)
    }
    
    public func remove(forKey key: String) {
        userDefaults.removeObject(forKey: key)
        userDefaults.synchronize()
    }
    
    public func exists(forKey key: String) -> Bool {
        return userDefaults.object(forKey: key) != nil
    }
}
