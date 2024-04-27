package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutPage extends BasePage{

    //protected WebDriver driver;
    public CheckoutPage (WebDriver driver){
        super(driver);
        //this.driver = driver;
    }

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindAll(@FindBy(css = "div[class='cart_item']"))
    List<WebElement> listOfProduct;

    @FindBy(id ="finish")
    private WebElement finishButton;

    public CheckoutPage fillInformation(String fname, String lname, String zcode){
        firstNameInput.sendKeys(fname);
        lastNameInput.sendKeys(lname);
        postalCodeInput.sendKeys(zcode);
        return this;
    }

    private String getProductName(WebElement product){
        return product.findElement(
                By.cssSelector("div[class='inventory_item_name']")).getText();
    }

    private String getProductPrice(WebElement product){
        return product.findElement(
                By.cssSelector("div[class='inventory_item_price']"))
                .getText().substring(1);
    }

    public Map<String, Double> getProductMap(){
        Map<String, Double> mapOfProducts = new HashMap<>();
        for (WebElement product: listOfProduct) {
            String name = getProductName(product);
            Double price = Double.parseDouble(getProductPrice(product));
            mapOfProducts.put(name,price);
        }
        for (Map.Entry<String, Double> entry2: mapOfProducts.entrySet()){
            System.out.println(entry2.getKey());
            System.out.println(entry2.getValue());
        }
        return mapOfProducts;
    }

    public CheckoutPage clickContinue(){
        continueButton.click();
        return this;
    }

    public CheckoutPage clickFinish(){
        finishButton.click();
        return this;
    }


}
