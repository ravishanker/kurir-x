//
//  LifecycleObserver.swift
//  Mobile SDK
//
//  Created by Crazybean Studio on 20/02/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation

open class LifecycleObserver {
    public func onViewWillAppear() {
    }
    
    public func onViewDidAppear() {
    }
    
    public func onViewWillDisappear() {
    }
    
    public func onViewDidDisappear() {
    }
    
    public func onViewDidInit() {
    }
    
    public func onViewDidDeinit() {
    }
}

extension LifecycleObserver: Equatable {
    public static func ==(lhs: LifecycleObserver, rhs: LifecycleObserver) -> Bool {
        // Compare the underlying instances
        return ObjectIdentifier(lhs).hashValue == ObjectIdentifier(rhs).hashValue
    }
}

extension LifecycleObserver: Hashable {
    public func hash(into hasher: inout Hasher) {
        hasher.combine(ObjectIdentifier(self).hashValue)
    }
}
