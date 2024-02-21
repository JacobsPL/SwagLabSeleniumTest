package test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

public class ItemListPageTest extends BaseTest{



    @BeforeMethod
    public void LogInAndGoToLandingPage(){
        loginPage.typeUsername("standard_user")
                .typePassword("secret_sauce")
                .clickLoginButton();
    }


    @Test
    public void TC007RedirectToItemPage() {

        WebElement testItem = itemListPage.getItem(0);
        String itemNameList = itemListPage.getItemName(testItem);
        itemListPage.getItemLink(testItem).click();
        String itemNameDetails = itemPage.getItemName();
        Assert.assertEquals(itemNameList,itemNameDetails);
    }

    @Test
    public void TC008AddItemToCart(){
        WebElement testItem = itemListPage.getItem(0);
        itemListPage.getAddToCartButton(testItem).click();
        Assert.assertEquals(itemListPage.getNumberOfItemsInCart(),1);
    }

    @Test
    public void TC009RemoveItemToCard(){
        // Preconditions - user has an item in cart
        WebElement testItem = itemListPage.getItem(0);
        itemListPage.getAddToCartButton(testItem).click();
        // end of preconditions

        int numOfItemsBeforeRemoval = itemListPage.getNumberOfItemsInCart();
        itemListPage.getRemoveFromCartButton(testItem).click();
        int numOfItemsAfterRemoval = itemListPage.getNumberOfItemsInCart();
        Assert.assertEquals(numOfItemsAfterRemoval,numOfItemsBeforeRemoval-1);

    }

    @Test
    public void TC010AddMultipleItems(){
        int counter = 0;
        for (WebElement item:itemListPage.getItemsList()) {
            itemListPage.getAddToCartButton(item).click();
            counter++;
        }

        Assert.assertEquals(counter,itemListPage.getNumberOfItemsInCart());
    }

    @Test
    public void TC011VerifyThatItemsAreNotRemovedFromCartAfterLogout(){
        WebElement testItem = itemListPage.getItem(0);
        itemListPage.getAddToCartButton(testItem).click();
        itemListPage.logout();
        LogInAndGoToLandingPage();
        Assert.assertEquals(itemListPage.getNumberOfItemsInCart(),1);
    }

    //TO DO:
    @Test
    public void TC012SortByNameAsc(){

        List<String> idealList = itemListPage.getProductNameList();
        Collections.sort(idealList);
        itemListPage.sortAtoZ();
        List<String> afterSortList  = itemListPage.getProductNameList();
        Assert.assertTrue(idealList.equals(afterSortList));
    }

    @Test
    public void TC013SortByNameDsc(){
        List<String> idealList = itemListPage.getProductNameList();
        Collections.sort(idealList, Collections.reverseOrder());
        itemListPage.sortZtoA();
        List<String> afterSortList  = itemListPage.getProductNameList();
        Assert.assertTrue(idealList.equals(afterSortList));
    }

    @Test
    public void TC014SortByPriceLowToHigh(){
       List<Float> idealList = itemListPage.getProductPriceList();
       Collections.sort(idealList);
       itemListPage.sortLowHigh();
       List<Float> afterSortList = itemListPage.getProductPriceList();
       Assert.assertTrue(idealList.equals(afterSortList));
    }

    @Test
    public void TC015SortByPriceHighToLow(){
        List<Float> idealList = itemListPage.getProductPriceList();
        Collections.sort(idealList,Collections.reverseOrder());
        itemListPage.sortHighLow();
        List<Float> afterSortList = itemListPage.getProductPriceList();
        Assert.assertTrue(idealList.equals(afterSortList));
    }
}
