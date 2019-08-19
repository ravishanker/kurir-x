//
//  LifecycleOwner.swift
//  Mobile SDK
//
//  Created by Crazybean Studio on 20/02/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Foundation

public protocol LifecycleOwner {
    var lifecycle: Lifecycle? { get }
}
