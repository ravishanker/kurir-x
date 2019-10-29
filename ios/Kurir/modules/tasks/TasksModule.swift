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
        // Worker
        factory(TasksWorker.self) { r in
            TasksWorker(userData: r.resolve(UserData.self),
                        usersRepository: r.resolve(UsersRepository.self)!,
                        tasksRepository: r.resolve(TasksRepository.self)!)
        }
        
        // Adviser
        factory(TasksAdviser.self) { (r, scene: TasksScene) -> TasksAdviser in
            TasksAdviser(scene: scene, worker: r.resolve(TasksWorker.self)!)
        }
    }
}
