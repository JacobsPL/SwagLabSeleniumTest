package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginPageTest extends BaseTest {



    @Test
    public void testToTest() throws InterruptedException {
        WebElement loginBox = driver.findElement(By.id("user-name"));
        loginBox.sendKeys("LOGIN0");
        sleep(1000);
    }

    @Test
    public void TC001HappyPathLogin(){
        loginPage.typeUsername("standard_user")
                    .typePassword("secret_sauce")
                        .clickLoginButton();

        String expectedLandingPageURL = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(expectedLandingPageURL,driver.getCurrentUrl());
    }

    @Test
    public void TC002WrongLogin(){
        loginPage.typeUsername("Wrong_user")
                    .typePassword("secret_sauce")
                        .clickLoginButton();


        String expectedErrorMesage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(expectedErrorMesage,loginPage.getErrorMessage());
    }

    @Test
    public void TC003WrongPassword(){
        loginPage.typeUsername("standard_use")
                    .typePassword("wrong_password")
                        .clickLoginButton();

        String expectedErrorMesage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(expectedErrorMesage,loginPage.getErrorMessage());
    }

    @Test
    public void TC004NoUsername(){
        loginPage.typeUsername("")
                    .typePassword("wrong_password")
                        .clickLoginButton();

        String expectedErrorMesage = "Epic sadface: Username is required";
        Assert.assertEquals(expectedErrorMesage,loginPage.getErrorMessage());

    }

    @Test
    public void TC005NoPassword(){
        loginPage.typeUsername("standard_use")
                    .typePassword("")
                        .clickLoginButton();

        String expectedErrorMesage = "Epic sadface: Password is required";
        Assert.assertEquals(expectedErrorMesage,loginPage.getErrorMessage());

    }

    @Test
    public void TC006BlockedUser(){
        loginPage.typeUsername("locked_out_user")
                .typePassword("secret_sauce")
                .clickLoginButton();

        String expectedErrorMesage = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(expectedErrorMesage,loginPage.getErrorMessage());
    }
}
