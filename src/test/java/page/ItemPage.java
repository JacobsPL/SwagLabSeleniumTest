package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ItemPage extends BasePage{

    public ItemPage(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "[class = 'inventory_details_name large_size']")
    private WebElement itemName;

    @FindBy(xpath = "//div[@class='inventory_details_desc_container']/button")
    private WebElement addAndRemoveButton;

    @FindBy(css = "[class = 'shopping_cart_badge']")
    private WebElement cartIcon;
    public WebElement getAddAndRemoveButton(){
        return addAndRemoveButton;
    }

    public String getItemName(){
        return itemName.getText();
    }

    public String getAddRemoveTextButton(){
        return addAndRemoveButton.getText();
    }

    public int getNumberInCart(){
        int numOfItems;

        try{
            numOfItems = Integer.parseInt(cartIcon.getText());
        }catch(NoSuchElementException ex){
            numOfItems = 0;
        }
        return numOfItems;
    }



}
