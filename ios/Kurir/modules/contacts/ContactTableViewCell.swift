//
//  ContactTableViewCell.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import UIKit
import Mobilex

class ContactTableViewCell: TableViewCell<User> {
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var emailLabel: UILabel!
    
    override func onSet(value: User?) {
        if let user = value {
            nameLabel.text = "\(user.firstName ?? "") \(user.lastName ?? "")"
            emailLabel.text = user.email
        }
    }
}
