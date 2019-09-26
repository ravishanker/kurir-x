//
//  FindModule.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Mobilex

class TasksModule: Module {
    override func inject() {
        // ViewModel
        factory(TasksViewModel.self) { r in
            TasksViewModel(userData: r.resolve(UserData.self),
                           usersRepository: r.resolve(UsersRepository.self)!,
                           tasksRepository: r.resolve(TasksRepository.self)!)
        }
        
        // Delegate
        factory(TasksDelegate.self) { (r, view: TasksView) -> TasksDelegate in
            TasksDelegate(view: view, viewModel: r.resolve(TasksViewModel.self)!)
        }
    }
}
