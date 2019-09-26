//
//  FindView.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Mobilex

protocol TasksView {
    func showSpinner()
    func hideSpinner()
    func showTasks(tasks: [Task])
    func showEmpty()
}
