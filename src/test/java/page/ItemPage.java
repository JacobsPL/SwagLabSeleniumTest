package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage extends BasePage{

    public ItemPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[class = 'inventory_details_name large_size']")
    private WebElement itemName;

    public String getItemName(){
        return itemName.getText();
    }

}
