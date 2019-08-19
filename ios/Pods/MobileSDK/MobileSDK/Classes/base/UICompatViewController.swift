//
//  BaseViewController.swift
//  Mobile SDK
//
//  Created by Crazybean Studio on 20/02/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import UIKit

open class UICompatViewController: UIViewController, LifecycleOwner {
    public var lifecycle: Lifecycle? = nil
    internal var selectors = [NSNotification.Name:Selector]()
    
    public override init(nibName nibNameOrNil: String?, bundle nibBundleOrNil: Bundle?) {
        super.init(nibName: nibNameOrNil, bundle: nibBundleOrNil)
        initialise()
    }
    
    public required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        initialise()
    }
    
    // Callbacks
    open override func viewDidLoad() {
        super.viewDidLoad()
        handleLifecycleEvent(event: .didInit)
    }
    
    open override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        handleLifecycleEvent(event: .willAppear)
    }
    
    open override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        handleLifecycleEvent(event: .didAppear)
        updateObserver(UIApplication.willResignActiveNotification, UIApplication.didEnterBackgroundNotification)
    }
    
    open override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        handleLifecycleEvent(event: .willDisappear)
    }
    
    open override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        handleLifecycleEvent(event: .didDisappear)
        updateObserver()
    }
    
    deinit {
        handleLifecycleEvent(event: .didDeinit)
    }
    
    private func initialise() {
        lifecycle = Lifecycle(owner: self)
        // Backgrounded
        selectors[UIApplication.willResignActiveNotification] = #selector(willResignActive)
        selectors[UIApplication.didEnterBackgroundNotification] = #selector(didEnterBackground)
        
        // Foreground
        selectors[UIApplication.willEnterForegroundNotification] = #selector(willEnterForeground)
        selectors[UIApplication.didBecomeActiveNotification] = #selector(didBecomeActive)
    }
    
    internal func handleLifecycleEvent(event: Lifecycle.Event) {
        lifecycle?.handleLifecycleEvent(event)
    }
}

extension UICompatViewController {
    internal func updateObserver(_ names: NSNotification.Name...) {
        NotificationCenter.default.removeObserver(self)
        for name in names {
            if let selector = selectors[name] {
                NotificationCenter.default.addObserver(self, selector: selector, name: name, object: nil)
            }
        }
    }
    
    @objc internal func willEnterForeground() {
        handleLifecycleEvent(event: .willAppear)
    }
    
    @objc internal func didBecomeActive() {
        handleLifecycleEvent(event: .didAppear)
        updateObserver(UIApplication.willResignActiveNotification, UIApplication.didEnterBackgroundNotification)
    }
    
    @objc internal func didEnterBackground() {
        handleLifecycleEvent(event: .didDisappear)
        updateObserver(UIApplication.willEnterForegroundNotification, UIApplication.didBecomeActiveNotification)
    }
    
    @objc internal func willResignActive() {
        handleLifecycleEvent(event: .willDisappear)
    }
}
