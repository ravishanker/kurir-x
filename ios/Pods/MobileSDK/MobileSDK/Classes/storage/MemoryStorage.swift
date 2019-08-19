//
//  MemoryStorage.swift
//  MobileSDK
//
//  Created by Loren on 6/3/19.
//

import Foundation

public class MemoryStorage: Storage {
    private var dictionary = [String:Any]()
  
    public init() {
    }
    
    public func set(_ value: Any?, forKey key: String) {
        dictionary[key] = value
    }
    
    public func set(_ value: Int, forKey key: String) {
        dictionary[key] = value
    }
    
    public func set(_ value: Bool, forKey key: String) {
        dictionary[key] = value
    }
    
    public func set(_ value: Float, forKey key: String) {
        dictionary[key] = value
    }
    
    public func set(_ value: Double, forKey key: String) {
        dictionary[key] = value
    }
    
    public func set(_ value: String?, forKey key: String) {
        dictionary[key] = value
    }
    
    public func object(forKey key: String) -> Any? {
        return dictionary[key]
    }
    
    public func objects(forKey key: String) -> [Any]? {
        return dictionary[key] as? [Any]
    }
    
    public func strings(forKey key: String) -> [String]? {
        return dictionary[key] as? [String]
    }
    
    public func integer(forKey key: String) -> Int {
        return dictionary[key] as? Int ?? 0
    }
    
    public func bool(forKey key: String) -> Bool {
        return dictionary[key] as? Bool ?? false
    }
    
    public func float(forKey key: String) -> Float {
        return dictionary[key] as? Float ?? 0.0
    }
    
    public func double(forKey key: String) -> Double {
        return dictionary[key] as? Double ?? 0.0
    }
    
    public func string(forKey key: String) -> String? {
        return dictionary[key] as? String
    }
    
    public func remove(forKey key: String) {
        if let index = dictionary.index(forKey: key) {
            dictionary.remove(at: index)
        }
    }
    
    public func exists(forKey key: String) -> Bool {
        return dictionary.index(forKey: key) != nil
    }
}
