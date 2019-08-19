//
//  MelonAlert.swift
//  Mobile SDK
//
//  Created by Crazybean Studio on 20/02/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import UIKit

public class MelonAlert {
    private let builder: Builder
    
    private init(builder: Builder) {
        self.builder = builder
    }
    
    public func show(animated flag: Bool = true, completion: (() -> Void)? = nil) {
        if let viewController = builder.viewController {
            let alert = UIAlertController(title: builder.title, message: builder.message, preferredStyle: builder.style)
            
            // Editors
            for handler in builder.handlers {
                alert.addTextField(configurationHandler: handler)
            }
            
            // Actions
            for action in builder.actions {
                alert.addAction(action)
            }
            
            viewController.present(alert, animated: flag, completion: completion)
        }
    }
    
    // Builder
    public class Builder {
        internal var title: String?
        internal var message: String?
        internal var actions = [UIAlertAction]()
        internal var handlers = [((UITextField) -> Void)?]()
        
        internal let style: UIAlertController.Style
        internal weak var viewController: UIViewController?
        
        public init(_ viewController: UIViewController?, style: UIAlertController.Style) {
            self.style = style
            self.viewController = viewController
        }
        
        public func setTitle(title: String) -> Builder {
            self.title = title
            return self
        }
        
        public func setMessage(message: String) -> Builder {
            self.message = message
            return self
        }
        
        public func setOptions(options: [String], style: UIAlertAction.Style = .default, handler: ((UIAlertAction) -> Void)? = nil) -> Builder {
            for option in options {
                let action = UIAlertAction(title: option, style: style, handler: handler)
                actions.append(action)
            }
            
            return self
        }
        
        public func addTextField(handler: ((UITextField) -> Void)?) -> Builder {
            handlers.append(handler)
            return self
        }
        
        public func addAction(title: String?, handler: ((UIAlertAction) -> Void)? = nil) -> Builder {
            return addAction(title: title, style: .default, handler: handler)
        }
        
        public func addAction(title: String?, style: UIAlertAction.Style = .default, handler: ((UIAlertAction) -> Void)? = nil) -> Builder {
            let action = UIAlertAction(title: title, style: style, handler: handler)
            actions.append(action)
            return self
        }
        
        public func build() -> MelonAlert {
            return MelonAlert(builder: self)
        }
    }
}
