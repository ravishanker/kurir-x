//
//  UIViewController+Dependency.swift
//  Kurir
//
//  Created by Loren on 18/8/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import UIKit

extension UIViewController {
    private class var dependency: Dependency? {
        get {
            return (UIApplication.shared.delegate as? AppDelegate)?.dependency
        }
    }
    
    internal class func resolve<Target: Any>(type: Target.Type) -> Target? {
        return dependency?.resolve(type: type)
    }
    
    internal class func resolve<Target: Any, Argument: Any>(type: Target.Type, argument: Argument) -> Target? {
        return dependency?.resolve(type: type, argument: argument)
    }
}
