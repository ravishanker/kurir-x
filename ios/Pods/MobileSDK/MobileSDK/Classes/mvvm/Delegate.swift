//
//  Delegate.swift
//  Mobile SDK
//
//  Created by Crazybean Studio on 20/02/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation

open class Delegate<V, VM: ViewModel>: LifecycleObserver, LifecycleOwner {
    public let view: V?
    public let viewModel: VM
    public var lifecycle: Lifecycle?
    private let canonicalName: String
    
    public init(view: V?, viewModel: VM) {
        self.view = view
        self.viewModel = viewModel
        canonicalName = String(describing: type(of: self))
    }
    
    public func authorise(_ lifecycleOwner: LifecycleOwner?, params payload: [String:Any?]? = nil) {
        if let lifecycle = lifecycleOwner?.lifecycle {
            self.lifecycle = lifecycle
            lifecycle.addObserver(self)
            
            // Invoke on initialise event.
            self.onCreateEvent(payload)
        }
    }
    
    open func onCreate(_ params: [String:Any?]? = nil) {
        print("\(canonicalName): onCreate")
    }
    
    open func onStart() {
        print("\(canonicalName): onStart")
    }
    
    open func onStop() {
        print("\(canonicalName): onStop")
    }
    
    open func onResume() {
        print("\(canonicalName): onResume")
    }
    
    open func onPause() {
        print("\(canonicalName): onPause")
    }
    
    open func onDestroy() {
        print("\(canonicalName): onDestroy")
    }
    
    private func onCreateEvent(_ params: [String:Any?]?) {
        lifecycle?.handleLifecycleEvent(.didInit)
        self.onCreate(params)
    }
    
    override public func onViewWillAppear() {
        self.onStart()
    }
    
    override public func onViewDidAppear() {
        self.onResume()
    }
    
    override public func onViewWillDisappear() {
        self.onPause()
    }
    
    override public func onViewDidDisappear() {
        self.onStop()
    }
    
    override public func onViewDidDeinit() {
        self.onDestroy()
    }
}
