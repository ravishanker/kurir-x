import UIKit
import Mobilex

class LoginViewController: BaseViewController<LoginActor, LoginScene>, LoginScene {
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
        viewController.modalPresentationStyle = .fullScreen
        self.present(viewController, animated: true, completion: nil)
    }
    
    func showNotFound() {
        showError("ERROR_USER_NOT_FOUND")
    }
    
    func showSpinner() {
        isIndicatorHidden = false
    }
    
    func hideSpinner() {
        isIndicatorHidden = true
    }
    
    func showRegister() {
    }
    
    @IBAction func onLoginTap(_ sender: Any) {
        actor?.onLoginClick(name: userNameField.text, password: passwordField.text)
    }
}
