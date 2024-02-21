package page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
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

    @FindBy (id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;

    @FindBy (id="remove-sauce-labs-backpack")
    private WebElement removeFromCartButton;
    @FindBy (css = "[class = 'shopping_cart_badge']")
    private WebElement itemInCartAmount;

    @FindBy (css = "[class = 'product_sort_container']")
    private WebElement sortDropdownElement;

    private Select sortDropdown;
    public Select getSortDropdown() {
        if (sortDropdown == null) {
            sortDropdown = new Select(sortDropdownElement);
        }
        return sortDropdown;
    }

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

    public void sortAtoZ(){
        getSortDropdown().selectByValue("az");
    }

    public void sortZtoA(){
        getSortDropdown().selectByValue("za");
    }

    public void sortLowHigh(){
        getSortDropdown().selectByValue("lohi");
    }

    public void sortHighLow(){
        getSortDropdown().selectByValue("hilo");
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
