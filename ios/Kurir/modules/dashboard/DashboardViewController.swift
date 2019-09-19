//
//  DashboardViewController.swift
//  Kurir
//
//  Created by Loren on 19/9/19.
//  Copyright © 2019 Crazybean Studio. All rights reserved.
//

import UIKit

fileprivate typealias Triple = (String, String, String)

class DashboardViewController: UITabBarController {
    override func viewDidLoad() {
        super.viewDidLoad()
        setBarItem()
    }
    
    private func setBarItem() {
        let assets = [Triple("", "ic_find", "ic_find_on"), Triple("", "ic_track", "ic_track_on"), Triple("", "ic_chat", "ic_chat_on"), Triple("", "ic_settings", "ic_settings_on")]
        for index in 0..<assets.count {
            let tabBarItem = (self.tabBar.items?[index])! as UITabBarItem
            let asset = assets[index]
            tabBarItem.setAssets(asset.0, normal: asset.1, focus: asset.2)
        }
    }
}

extension UITabBarItem {
    func setAssets(_ titleRes: String, normal: String, focus: String) {
        image = UIImage(named: normal)?.withRenderingMode(UIImage.RenderingMode.alwaysOriginal)
        selectedImage = UIImage(named: focus)?.withRenderingMode(UIImage.RenderingMode.alwaysOriginal)
        //title = getString(titleRes)
    }
}
