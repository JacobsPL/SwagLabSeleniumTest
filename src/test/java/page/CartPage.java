package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{

    //WebDriver driver;
    public CartPage (WebDriver driver){
        super(driver);
        //this.driver = driver;
    }
    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    public CheckoutPage clickCheckoutButton(){
        checkoutButton.click();
        return new CheckoutPage(driver);
    }
}
