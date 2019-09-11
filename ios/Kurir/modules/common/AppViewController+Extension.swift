//
//  UIViewController+Dependency.swift
//  Kurir
//
//  Created by Loren on 18/8/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import UIKit
import Crazybean

extension AppViewController {
    var isIndicatorHidden: Bool {
        get {
            return indicatorView?.isHidden ?? true
        }
        
        set(value) {
            guard let view = indicatorView else { return }
            view.isHidden = value
            
            if value {
                // Remove the item from view
                view.removeFromSuperview()
            } else {
                self.view.addSubview(view)
                view.startAnimating()
            }
        }
    }
    
    private var indicatorView: UIActivityIndicatorView? {
        get {
            if let indicatorView = get(forType: UIActivityIndicatorView.self) {
                return indicatorView
            } else {
                let target = UIActivityIndicatorView(style: .gray)
                target.color = UIColor(hex: "#6558f5")
                target.center = self.view.center
                
                set(forType: UIActivityIndicatorView.self, target: target)
                
                return target
            }
        }
    }
}

extension AppViewController {
    func showError(_ resId: String) {
        MelonAlert.Builder(self, style: .alert)
        .setTitle(getString("ERROR_TITLE"))
        .setMessage(getString(resId))
        .addAction(getString("BUTTON_GOTCHA"))
        .build()
        .show()
    }
}

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

extension UIViewController {
    func getString(_ key: String)-> String {
        return NSLocalizedString(key, comment: "")
    }
}

extension NSDictionary {
    func getObject<Target: Any>(type: Target.Type) -> Target? {
        return self.first { key, value -> Bool in
            (value as? Target) != nil
            } as? Target
    }
}

