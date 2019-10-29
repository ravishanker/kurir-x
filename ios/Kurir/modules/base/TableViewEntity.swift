//
//  TableViewEntity.swift
//  Kurir
//
//  Created by Loren on 10/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import UIKit

class TableViewCell<TYPE>: UITableViewCell {
    var entity: TYPE? = nil {
        willSet(value) {
            onSet(value: value)
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        // Configure the view for the selected state
    }
    
    open func onSet(value: TYPE?) {
        // Empty implementation.
    }
}
