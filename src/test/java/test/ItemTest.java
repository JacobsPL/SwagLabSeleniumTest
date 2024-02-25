package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ItemTest extends BaseTest{

    @BeforeMethod
    public void loginAndGoToItemPage(){
        logInAsStandardUser();
        itemListPage.getItemLink(itemListPage.getItem(0)).click();
    }

    @Test
    public void TC016AddToCart(){
        itemPage.getAddAndRemoveButton().click();
        Assert.assertEquals(itemPage.getNumberInCart(),1);
        Assert.assertEquals(itemPage.getAddRemoveTextButton(),"Remove");
    }

    @Test
    public void TC017RemoveFromCart(){
        itemPage.getAddAndRemoveButton().click();
        itemPage.getAddAndRemoveButton().click();
        Assert.assertEquals(itemPage.getNumberInCart(),0);
        Assert.assertEquals(itemPage.getAddRemoveTextButton(),"Add to cart");
    }
}
