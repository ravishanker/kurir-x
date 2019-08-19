//
//  Module.swift
//  Kurir
//
//  Created by Loren on 19/8/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Swinject
import Mobilex

class Module {
    static let container = Container() // TODO: Remove the static variable
    private lazy var singletons = [String : Any]()
    
    init() {
        inject()
    }
    
    func resolve<Target: Any>(type: Target.Type) -> Target? {
        return Module.container.resolve(type)
    }
    
    func resolve<Target: Any, Argument: Any>(type: Target.Type, argument: Argument) -> Target? {
        return Module.container.resolve(type, argument: argument)
    }
    
    func fetch<Target: Any>(forType type: Target.Type, factory: (() -> Target?)? = nil) -> Target? {
        var instance = singletons[key(forType: type)] as? Target
        if instance == nil {
            instance = factory?()
            self.singletons[key(forType: type)] = instance
        }
        
        return instance
    }
    
    public func register<Target>(_ type: Target.Type, name: String? = nil, factory: @escaping (Resolver) -> Target) {
        Module.container.register(type, name: name, factory: factory)
    }
    
    public func register<Target, Argument>(_ type: Target.Type, name: String? = nil, factory: @escaping (Resolver, Argument) -> Target) {
        Module.container.register(type, name: name, factory: factory)
    }
    
    func inject() {
        preconditionFailure("You must override register method")
    }
}

private func key<Target: Any>(forType type: Target.Type) -> String {
    return String(describing: type)
}

