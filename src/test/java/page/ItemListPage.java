package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ItemListPage extends BasePage{

    public ItemListPage(WebDriver driver){
        super(driver);
    }

    @FindBy (id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;

    @FindBy (id="remove-sauce-labs-backpack")
    private WebElement removeFromCartButton;

    @FindAll (@FindBy(css = "[class = inventory_item]"))
    private List<WebElement> itemList;


    public List<WebElement> getItemsList(){
        return itemList;
    }

    public String getItemPrice(WebElement item){
        return item.findElement(By.className("inventory_item_price")).getText();
    }

    public String getItemName(WebElement item){
        return item.findElement(By.className("inventory_item_name")).getText();
    }

    public List<String> getProductNameList(){
        List<String> nameList = new ArrayList<>();

        for(WebElement i: itemList) {
            String itemName = i.findElement(By.cssSelector("[class = 'inventory_item_name ']")).getText();
            nameList.add(itemName);
        }
        return nameList;
    }

    public List<Float> getProductPriceList(){
        List<Float> priceList = new ArrayList<>();
        for(WebElement i: itemList) {
            String itemPriceString = i.findElement(By.cssSelector("[class = 'inventory_item_price']")).getText();
            Float itemPrice = Float.parseFloat(itemPriceString.substring(1));
            priceList.add(itemPrice);
        }
        return priceList;
    }

    public WebElement getItemLink(WebElement item){
        return item.findElement(By.className("inventory_item_name"));
    }

    public WebElement getItem(int index){
        return itemList.get(index);
    }

    public WebElement getAddToCartButton(WebElement item){
        return item.findElement(By.cssSelector("[class='btn btn_primary btn_small btn_inventory ']"));
    }

    public WebElement getRemoveFromCartButton(WebElement item){
        return item.findElement(By.id("remove-sauce-labs-backpack"));
    }

}
