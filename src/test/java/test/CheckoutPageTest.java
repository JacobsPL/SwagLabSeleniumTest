package test;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CheckoutPage;

import java.util.HashMap;
import java.util.Map;

public class CheckoutPageTest extends BaseTest {

    protected CheckoutPage checkoutPage;
    @BeforeMethod
    public void CheckoutPageTestSetUp(){
        this.checkoutPage = new CheckoutPage(driver);
        logInAsStandardUser();
    }

    @Test
    public void TC018ItemsInCheckoutSameAsInCart(){
        Map<String,Double> mapOfItems = new HashMap<>();

        for(int i = 0; i<3;i++) {
            WebElement item = itemListPage.getItem(i);
            itemListPage.getAddToCartButton(item).click();
            String name = itemListPage.getItemName(item);
            Double price = Double.parseDouble(itemListPage.getItemPrice(item)
                    .substring(1));
            mapOfItems.put(name,price);
        }
        basePage.clickOnCartButton()
                .clickCheckoutButton()
                .fillInformation
                        ("Jakub","Krawczyk", "00-761")
                .clickContinue();

        Map<String, Double> mapOfProductsInCheckout =
                checkoutPage.getProductMap();

        Assert.assertTrue(mapsAreEqual(mapOfItems,mapOfProductsInCheckout));
    }
}
