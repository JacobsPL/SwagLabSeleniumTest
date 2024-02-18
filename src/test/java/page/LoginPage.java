package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy (id = "login-button")
    private WebElement loginButton;

    @FindBy (css = "[data-test='error']")
    private WebElement errorLabel;


    public WebElement getPasswordInput(){
        return passwordInput;
    }

    public WebElement getLoginButton(){
        return loginButton;
    }

    public WebElement getUserNameInput(){
        return usernameInput;
    }

    public WebElement getErrorLabel(){
        return errorLabel;
    }
    public LoginPage typeUsername(String username){
        usernameInput.sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password){
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton(){
        loginButton.click();
        return this;
    }

    public String getErrorMessage(){
        return errorLabel.getText();
    }



}
