//
//  ViewController.swift
//  Kurir
//
//  Created by Loren on 27/10/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import UIKit
import Mobilex

open class AppViewController: UIViewController, PulseOwner {
    public lazy var pulse: Pulse? = Pulse(owner: self)
    private lazy var targets = [String : Any?]()

    // Selectors
    private lazy var selectors: [NSNotification.Name:Selector] = [
        // Backgrounded
        UIApplication.willResignActiveNotification: #selector(willResignActive),
        UIApplication.didEnterBackgroundNotification: #selector(didEnterBackground),
        
        // Foreground
        UIApplication.willEnterForegroundNotification: #selector(willEnterForeground),
        UIApplication.didBecomeActiveNotification: #selector(didBecomeActive)
    ]
    
    // Parameters for receiving data from outside
    private(set) public var params: [String : Any]? = nil
    
    // Params cache for targets
    private lazy var payloads = [String:[String:Any]]()
    
    // Callbacks
    open override func viewDidLoad() {
        super.viewDidLoad()
        pulse?.handleEvent(event: Pulse.Event.onload)
    }
    
    open override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        pulse?.handleEvent(event: Pulse.Event.onappear)
    }
    
    open override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        pulse?.handleEvent(event: Pulse.Event.onactivate)
        updateObserver(UIApplication.willResignActiveNotification, UIApplication.didEnterBackgroundNotification)
    }
    
    open override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        pulse?.handleEvent(event: Pulse.Event.ondeactivate)
    }
    
    open override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        pulse?.handleEvent(event: Pulse.Event.ondismiss)
        updateObserver()
    }
    
    deinit {
        pulse?.handleEvent(event: Pulse.Event.onrelease)
        targets.removeAll()
    }
    
    public func set<Target: Any>(forType type: Target.Type, target: Target?, label: String? = nil) {
        if let target = target {
            targets[key(forType: type, label: label)] = target
        }
    }
    
    public func get<Target: Any>(forType type: Target.Type, label: String? = nil)-> Target? {
        if let target = targets[key(forType: type, label: label)] {
            return target as? Target
        }
        
        return nil
    }
    
    public func performSegue(withIdentifier identifier: String, sender: Any?, object: Any?) {
        if let object = object {
            var params = [String:Any]()
            var key: String
            if let clazz = object_getClass(object) {
                key = NSStringFromClass(clazz)
            } else {
                key = String(describing: type(of: self))
            }
            params[key] = object
            payloads[identifier] = params
        }
        super.performSegue(withIdentifier: identifier, sender: sender)
    }
    
    public func performSegue(withIdentifier identifier: String, sender: Any?, params: [String : Any]?) {
        if let params = params {
            payloads[identifier] = params
        }
        super.performSegue(withIdentifier: identifier, sender: sender)
    }
    
    open override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let identifier = segue.identifier, let params = payloads[identifier], let target = segue.destination as? AppViewController {
            target.params = params
        }
    }
}

fileprivate func key<Target: Any>(forType type: Target.Type, label: String? = nil) -> String {
    return "\(String(describing: type))-\(label ?? "")"
}

extension AppViewController {
    internal func updateObserver(_ names: NSNotification.Name...) {
        NotificationCenter.default.removeObserver(self)
        for name in names {
            if let selector = selectors[name] {
                NotificationCenter.default.addObserver(self, selector: selector, name: name, object: nil)
            }
        }
    }
    
    @objc internal func willEnterForeground() {
        pulse?.handleEvent(event: Pulse.Event.ondeactivate)
    }
    
    @objc internal func didBecomeActive() {
        pulse?.handleEvent(event: Pulse.Event.onactivate)
        updateObserver(UIApplication.willResignActiveNotification, UIApplication.didEnterBackgroundNotification)
    }
    
    @objc internal func didEnterBackground() {
        pulse?.handleEvent(event: Pulse.Event.ondismiss)
        updateObserver(UIApplication.willEnterForegroundNotification, UIApplication.didBecomeActiveNotification)
    }
    
    @objc internal func willResignActive() {
        pulse?.handleEvent(event: Pulse.Event.onappear)
    }
}
