import UIKit
import Mobilex
import Crazybean

class LoginViewController: AppViewController, LoginView {
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
        showError("ERROR_WRONG_PASSWORD")
    }
    
    func showDashboard() {
    }
    
    func showNotFound() {
        showError("ERROR_USER_NOT_FOUND")
    }
    
    func dismiss() {
    }
    
    func showSpinner() {
        isIndicatorHidden = false
    }
    
    func hideSpinner() {
        isIndicatorHidden = true
    }
    
    @IBAction func onLoginTap(_ sender: Any) {
        delegate?.onLoginClick(name: userNameField.text, password: passwordField.text)
    }
}
