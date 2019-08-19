//
//  ViewModel.swift
//  Mobile SDK
//
//  Created by Crazybean Studio on 20/02/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation

open class ViewModel {
    public init() {
    }
    
    deinit {
        onRelease()
    }
    
    open func onRelease() {
        // Call when ViewModel released.
    }
}
