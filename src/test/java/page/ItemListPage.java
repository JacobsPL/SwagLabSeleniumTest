package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

public class ItemListPage extends BasePage{

    public ItemListPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "shopping_cart_container")
    private WebElement cartIcon;

    @FindBy (id = "react-burger-menu-btn")
    private WebElement hamburgerMenu;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItemsOption;

    @FindBy (id = "about_sidebar_link")
    private WebElement aboutOption;

    @FindBy (id = "logout_sidebar_link")
    private WebElement logoutOption;


    @FindBy (css = "[class = 'select_container']")
    private WebElement sortList;

    @FindBy (id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;

    @FindBy (id="remove-sauce-labs-backpack")
    private WebElement removeFromCartButton;
    @FindBy (css = "[class = 'shopping_cart_badge']")
    private WebElement itemInCartAmount;

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

    public int getNumberOfItemsInCart(){
        int numOfItems;

        try{
            numOfItems = Integer.parseInt(itemInCartAmount.getText());
        }catch(NoSuchElementException ex){
            numOfItems = 0;
        }
        return numOfItems;
    }

    public void logout(){
        hamburgerMenu.click();
        logoutOption.click();
    }
}
