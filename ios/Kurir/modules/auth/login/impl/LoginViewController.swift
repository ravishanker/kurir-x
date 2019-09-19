import UIKit
import Mobilex
import Crazybean

class LoginViewController: BaseViewController<LoginView, LoginViewModel, LoginDelegate>, LoginView {
    @IBOutlet weak var userNameField: UITextField!
    @IBOutlet weak var passwordField: UITextField!
    
    func showError(auth: Auth?) {
    }
    
    func showNameError() {
    }
    
    func showPasswordError() {
        showError("ERROR_WRONG_PASSWORD")
    }
    
    func showDashboard() {
        let storyboard = UIStoryboard(name: "Dashboard", bundle: nil)
        let viewController = storyboard.instantiateViewController(withIdentifier: "Dashboard")
        self.present(viewController, animated: true, completion: nil)
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
