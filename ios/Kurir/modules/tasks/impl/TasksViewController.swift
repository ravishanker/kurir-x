//
//  FindViewController.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import Crazybean
import Mobilex

class TasksViewController: BaseViewController<TasksView, TasksViewModel, TasksDelegate>, TasksView, UITableViewDelegate, UITableViewDataSource {
    private var tasks: [Task]? = nil
    weak var tableView: UITableView? = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView?.rowHeight = UITableView.automaticDimension
        tableView?.register(forType: TaskTableViewCell.self)
    }
    
    func showSpinner() {
        isIndicatorHidden = false
    }
    
    func hideSpinner() {
        isIndicatorHidden = true
    }
    
    func showTasks(tasks: [Task]) {
        self.tasks = tasks
        tableView?.reloadData()
    }
    
    func showEmpty() {
        showError("ERROR_GENERIC")
    }
    
    // UITableView
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return tasks?.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(forType: TaskTableViewCell.self)
        cell?.entity = tasks?[indexPath.row]
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
    }
}
