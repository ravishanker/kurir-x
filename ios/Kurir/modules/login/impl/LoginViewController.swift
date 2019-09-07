import UIKit
import Mobilex
import MobileSDK

class LoginViewController: UICompatViewController, LoginView {
    @IBOutlet weak var userNameField: UITextField!
    @IBOutlet weak var passwordField: UITextField!
    
    private(set) lazy var delegate = UIViewController.resolve(type: LoginDelegate.self, argument: self as LoginView)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        delegate?.authorise(self)
    }
    
    func showError(auth: Auth?) {
    }
    
    func showNameError() {
    }
    
    func showPasswordError() {
    }
    
    func showRegister() {
        // Goto Signup ViewController
    }
    
    func showDashboard() {
    }
    
    func showNotFound() {
    }
    
    func dismiss() {
    }
    
    @IBAction func onLoginTap(_ sender: Any) {
        delegate?.onLoginClick(name: userNameField.text, password: passwordField.text)
    }
    
    @IBAction func onSignupTap(_ sender: Any) {
        delegate?.onSignupClick()
    }
}
