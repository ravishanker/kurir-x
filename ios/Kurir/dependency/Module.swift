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
    internal var container: Container?
    internal var singletons: [String : Any]?
    
    func resolve<Target: Any>(type: Target.Type) -> Target? {
        return container?.resolve(type)
    }
    
    func resolve<Target: Any, Argument: Any>(type: Target.Type, argument: Argument) -> Target? {
        return container?.resolve(type, argument: argument)
    }
    
    func single<Target: Any>(_ type: Target.Type, resolver: ((Resolver) -> Target?)? = nil) {
        factory(type) { r in
            return self.fetch(forType: type, resolver: r, factory: resolver)!
        }
    }
    
    func factory<Target>(_ type: Target.Type, resolver: @escaping (Resolver) -> Target) {
        container?.register(type, factory: resolver)
    }
    
    func factory<Target, Argument>(_ type: Target.Type, resolver: @escaping (Resolver, Argument) -> Target) {
        container?.register(type, factory: resolver)
    }
    
    internal func inject() {
        preconditionFailure("You must override register method")
    }
    
    private func fetch<Target: Any>(forType type: Target.Type, resolver: Resolver, factory: ((Resolver) -> Target?)? = nil) -> Target? {
        var instance = singletons?[key(forType: type)] as? Target
        if instance == nil {
            instance = factory?(resolver)
            singletons?[key(forType: type)] = instance
        }
        
        return instance
    }
}

private func key<Target: Any>(forType type: Target.Type) -> String {
    return String(describing: type)
}

