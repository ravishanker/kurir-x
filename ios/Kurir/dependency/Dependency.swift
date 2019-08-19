//
//  DependencyImpl.swift
//  mobilex
//
//  Created by Loren on 17/8/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation
import Swinject

class Dependency {
    private var mModules = [Module]()
    
    init(_ modules: Module...) {
        for module: Module in modules {
            mModules.append(module)
        }
    }
    
    func resolve<Target: Any>(type: Target.Type) -> Target? {
        for module in mModules {
            if let target = module.resolve(type: type) {
                return target
            }
        }
        
        return nil
    }
    
    func resolve<Target: Any, Argument: Any>(type: Target.Type, argument: Argument) -> Target? {
        for module in mModules {
            if let target = module.resolve(type: type, argument: argument) {
                return target
            }
        }
        
        return nil
    }
}
