//
//  Dependency.swift
//  mobtracker
//
//  Created by Crazybean Studio on 16/11/18.
//  Copyright © 2018 Crazybean Studio. All rights reserved.
//

import Foundation

public protocol Dependency {
    func resolve<Target: Any>(type: Target.Type) -> Target?
    func resolve<Target: Any, Argument: Any>(type: Target.Type, argument: Argument) -> Target?
}
