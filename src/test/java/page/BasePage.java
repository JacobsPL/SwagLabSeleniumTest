package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

    protected WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[class = 'shopping_cart_badge']")
    protected WebElement itemInCartAmount;

    @FindBy (id = "logout_sidebar_link")
    protected WebElement logoutOption;

    @FindBy (id = "about_sidebar_link")
    protected WebElement aboutOption;

    @FindBy (id = "shopping_cart_container")
    protected WebElement cartIcon;

    @FindBy (id = "react-burger-menu-btn")
    protected WebElement hamburgerMenu;

    @FindBy(id = "inventory_sidebar_link")
    protected WebElement allItemsOption;

    @FindBy (css = "[class = 'product_sort_container']")
    private WebElement sortDropdownElement;

    private Select sortDropdown;
    public Select getSortDropdown() {
        if (sortDropdown == null) {
            sortDropdown = new Select(sortDropdownElement);
        }
        return sortDropdown;
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
    public CartPage clickOnCartButton(){
        cartIcon.click();
        return new CartPage(this.driver);
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
}
