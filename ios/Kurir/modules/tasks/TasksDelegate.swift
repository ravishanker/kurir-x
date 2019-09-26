//
//  FindDelegate.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Crazybean

class TasksDelegate: Delegate<TasksView, TasksViewModel> {
    override func onCreate(_ params: [String : Any?]? = nil) {
        super.onCreate(params)
        onRefresh()
    }
    
    private func onRefresh() {
        view?.showSpinner()
        viewModel.loadTasks().observe(for: self) { [weak self] tasks in
            self?.view?.hideSpinner()
            guard let tasks = tasks else {
                self?.view?.showEmpty()
                return
            }
            
            self?.view?.showTasks(tasks: tasks)
        }
    }
}
