//
//  FindViewModel.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Crazybean
import Mobilex

class TasksViewModel: ViewModel {
    private let userData: UserData?
    private let usersRepository: UsersRepository
    private let tasksRepository: TasksRepository
    
    init(userData: UserData?, usersRepository: UsersRepository, tasksRepository: TasksRepository) {
        self.userData = userData
        self.usersRepository = usersRepository
        self.tasksRepository = tasksRepository
    }
    
    func loadTasks() -> LiveData<[Task]?> {
        return LiveData<[Task]?>() { [weak self] liveData in
            self?.tasksRepository.getTasks(picker: nil, completion: { tasks in
                liveData.value = tasks
            })
        }
    }
}

