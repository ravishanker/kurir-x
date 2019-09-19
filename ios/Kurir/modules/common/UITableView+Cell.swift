//
//  UITableView+Cell.swift
//  Kurir
//
//  Created by Loren on 10/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import UIKit

extension UITableView {
    func register<CELL: UITableViewCell>(forType type: CELL.Type) {
        let identifier = String(describing: type)
        let nib = UINib(nibName: identifier, bundle: nil)
        register(nib, forCellReuseIdentifier: identifier)
    }
    
    func dequeueReusableCell<CELL: UITableViewCell>(forType type: CELL.Type) -> CELL? {
        let identifier = String(describing: type)
        return dequeueReusableCell(withIdentifier: identifier) as? CELL
    }
}
