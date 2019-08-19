//
//  Lifecycle.swift
//  Mobile SDK
//
//  Created by Crazybean Studio on 20/02/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation

public class Lifecycle {
    // Lifecycle Events
    enum Event {
        case didInit, willAppear, didAppear, willDisappear, didDisappear, didDeinit
    }
    
    // Lifecycle State
    enum State : Int {
        case destroyed, created, paused, stopped, started, resumed
        
        func isAtLeast(state: State) -> Bool {
            return rawValue >= state.rawValue
        }
    }
    
    var shouldBeActive: Bool {
        get {
            return current.isAtLeast(state: State.started)
        }
    }
    
    var isReleasing: Bool {
        get {
            return current == .destroyed
        }
    }
    
    // Member instances.
    private let lifecycleOwner: LifecycleOwner?
    private var current: State = .destroyed
    private var observerMap = [Int: LifecycleObserver]()
    
    init(owner lifecycleOwner: LifecycleOwner) {
        self.lifecycleOwner = lifecycleOwner
    }
    
    func addObserver(_ observer: LifecycleObserver) {
        observerMap[observer.hashValue] = observer
    }
    
    func removeObserver(_ observer: LifecycleObserver) {
        observerMap.removeValue(forKey: observer.hashValue)
    }
    
    func handleLifecycleEvent(_ event: Event) {
        // sync
        current = sync(event)
    }
    
    private func sync(_ event: Event) -> State {
        var state = State.destroyed
        if let _ = lifecycleOwner?.lifecycle {
            for element in observerMap.values {
                switch event {
                case .didInit:
                    element.onViewDidInit()
                    state = State.created
                    
                case .willAppear:
                    element.onViewWillAppear()
                    state = State.started
                    
                case .didAppear:
                    element.onViewDidAppear()
                    state = State.resumed
                    
                case .willDisappear:
                    element.onViewWillDisappear()
                    state = State.paused
                    
                case .didDisappear:
                    element.onViewDidDisappear()
                    state = State.stopped
                    
                case .didDeinit:
                    element.onViewDidDeinit()
                    state = State.destroyed
                }
            }
        }
        
        return state
    }
}
