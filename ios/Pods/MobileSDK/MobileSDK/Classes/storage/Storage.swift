//
//  Storage.swift
//  Mobile SDK
//
//  Created by Crazybean Studio on 20/02/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation

public protocol Storage {
    // Save value
    func set(_ value: Any?, forKey key: String)
    func set(_ value: Int, forKey key: String)
    func set(_ value: Bool, forKey key: String)
    func set(_ value: Float, forKey key: String)
    func set(_ value: Double, forKey key: String)
    func set(_ value: String?, forKey key: String)
    
    // Fetch value
    func object(forKey key: String) -> Any?
    func objects(forKey key: String) -> [Any]?
    func strings(forKey key: String) -> [String]?
    func integer(forKey key: String) -> Int
    func bool(forKey key: String) -> Bool
    func float(forKey key: String) -> Float
    func double(forKey key: String) -> Double
    func string(forKey key: String) -> String?
    
    // Remove key.
    func remove(forKey key: String)
    
    // Check exists
    func exists(forKey key: String) -> Bool
}
