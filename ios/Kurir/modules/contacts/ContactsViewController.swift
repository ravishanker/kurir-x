//
//  ContactsViewController.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import UIKit
import Mobilex

class ContactsViewController: BaseViewController<ContactsActor, ContactsScene>, ContactsScene, UITableViewDelegate, UITableViewDataSource {
    private var contacts: [User]? = nil
    
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.rightBarButtonItem = UIBarButtonItem(barButtonSystemItem: .add, target: self, action: #selector(addTapped))
        
        tableView.rowHeight = UITableView.automaticDimension
        tableView.register(forType: ContactTableViewCell.self)
    }
    
    func showExplore() {
        // Launch Explore view controller
        if let viewController = self.storyboard?.instantiateViewController(withIdentifier: "Explore") {
            viewController.modalPresentationStyle = .fullScreen
            self.present(viewController, animated: true, completion: nil)
        }
    }
    
    func showContacts(users: [User]) {
        contacts = users
        tableView.reloadData()
    }
    
    func showChat(user: User) {
        performSegue(withIdentifier: "showChat", sender: self, object: user)
    }
    
    func showEmpty() {
        tableView.reloadData()
    }
    
    func showSpinner() {
    }
    
    func hideSpinner() {
    }
    
    @objc private func addTapped(_ sender: Any) {
        actor?.onAddClick()
    }
    
    // UITableView
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return contacts?.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(forType: ContactTableViewCell.self)
        cell?.entity = contacts?[indexPath.row]
        return cell!
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
    }
}
