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
        // Wrapper
        factory(TasksWrapper.self) { r in
            TasksWrapper(userData: r.resolve(UserData.self),
                         usersRepository: r.resolve(UsersRepository.self)!,
                         tasksRepository: r.resolve(TasksRepository.self)!)
        }
        
        // Actor
        factory(TasksActor.self) { (r, scene: TasksScene) -> TasksActor in
            TasksActor(scene: scene, wrapper: r.resolve(TasksWrapper.self)!)
        }
    }
}
