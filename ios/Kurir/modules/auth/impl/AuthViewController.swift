import UIKit
import Mobilex
import MobileSDK

class AuthViewController: UICompatViewController, AuthView {
    private(set) lazy var delegate = UIViewController.resolve(type: AuthDelegate.self, argument: self as AuthView)
    
    @IBOutlet weak var textLabel: UILabel!
    
    @IBAction func onLoadTouch(_ sender: Any) {
        showLoading()
    }
    
    func showLogin(auth: Auth?) {
    }
    
    func showRegister() {
    }
    
    func showLoading() {
        textLabel.text = "Loading"
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        delegate?.authorise(self)
    }
}
