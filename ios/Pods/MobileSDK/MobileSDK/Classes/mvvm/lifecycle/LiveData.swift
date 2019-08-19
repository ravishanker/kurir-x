//
//  LiveData.swift
//  Mobile SDK
//
//  Created by Crazybean Studio on 20/02/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation

public class LiveData<T> {
    private var wrapper: Wrapper<T>? = nil
    private lazy var observers = [String:Observer<T>]()
    
    public var value: T {
        get {
            return wrapper?.data as! T // Ignore it!
        }
        
        set {
            if wrapper == nil {
                wrapper = Wrapper(data: newValue)
            } else {
                wrapper?.data = newValue
            }
            
            for observer in observers.values {
                notify(observer)
            }
        }
    }
    
    public func observe(completion: @escaping (T) -> Void) {
        addObserver(owner: nil, completion: completion, forever: true)
    }
    
    public func observe(for owner: LifecycleOwner, completion: @escaping (T) -> Void) {
        addObserver(owner: owner, completion: completion)
    }
    
    public init(_ block: ((LiveData<T>) -> Void)? = nil) {
        block?(self)
    }
    
    private func addObserver(owner: LifecycleOwner?, completion: @escaping (T) -> Void, forever: Bool = false) {
        var local = completion
        var key: String = ""
        withUnsafePointer(to: &local) {
            key = "\($0)"
        }
        
        if let _ = observers[key] {
            return print("Error: Observer already exists for \(String(describing: owner))")
        }
        
        // Add the observer to map.
        if owner?.lifecycle?.isReleasing ?? false {
            return print("\(String(describing: owner)) is releasing")
        }
        
        let observer = Observer(owner: owner, block: completion, forever: forever)
        observers[key] = observer
        notify(observer)
    }
    
    private func notify(_ observer: Observer<T>) {
        guard let wrapper = wrapper else {
            return
        }
        
        observer.notify(data: wrapper.data)
    }
    
    // Data Wrapper
    private class Wrapper<T> {
        var data: T
        
        init(data: T) {
            self.data = data
        }
    }
    
    // Observer
    private class Observer<T> {
        private let forever: Bool
        private let block: (T) -> Void
        private let owner: LifecycleOwner?
        
        init(owner: LifecycleOwner?, block: @escaping (T) -> Void, forever: Bool = false) {
            self.owner = owner
            self.block = block
            self.forever = forever
        }
        
        func notify(data: T) {
            if forever || isTesting {
                block(data)
            } else if let lifecycle = owner?.lifecycle {
                if lifecycle.shouldBeActive {
                    block(data)
                }
            }
        }
        
        private var isTesting: Bool {
            get {
                if let _ = NSClassFromString("XCTest") {
                    return true
                }
                
                return false
            }
        }
    }
}
