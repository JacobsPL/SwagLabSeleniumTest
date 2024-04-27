package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginPageTest extends BaseTest {

    public final static String USER_NOT_FOUND_ERROR = "Epic sadface: Username and password do not match any user in this service";
    public final static String NO_USERNAME_ERROR = "Epic sadface: Username is required";
    public final static String NO_PASSWORD_ERROR = "Epic sadface: Password is required";
    public final static String USER_BLOCKED_ERROR = "Epic sadface: Sorry, this user has been locked out.";

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

        Assert.assertEquals(USER_NOT_FOUND_ERROR,loginPage.getErrorMessage());
    }

    @Test
    public void TC003WrongPassword(){
        loginPage.typeUsername("standard_use")
                    .typePassword("wrong_password")
                        .clickLoginButton();

        Assert.assertEquals(USER_NOT_FOUND_ERROR,loginPage.getErrorMessage());
    }

    @Test
    public void TC004NoUsername(){
        loginPage.typeUsername("")
                    .typePassword("wrong_password")
                        .clickLoginButton();

        Assert.assertEquals(NO_USERNAME_ERROR,loginPage.getErrorMessage());

    }

    @Test
    public void TC005NoPassword(){
        loginPage.typeUsername("standard_use")
                    .typePassword("")
                        .clickLoginButton();

        Assert.assertEquals(NO_PASSWORD_ERROR,loginPage.getErrorMessage());

    }

    @Test
    public void TC006BlockedUser(){
        loginPage.typeUsername("locked_out_user")
                .typePassword("secret_sauce")
                .clickLoginButton();

        Assert.assertEquals(USER_BLOCKED_ERROR,loginPage.getErrorMessage());
    }

    @Test(dataProvider = "loginData")
    public void TC020CSVUsersLogin(String username, String password){
        loginPage.typeUsername(username)
                .typePassword(password)
                .clickLoginButton();

        String expectedLandingPageURL = "https://www.saucedemo.com";
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedLandingPageURL));
    }

    @DataProvider(name = "loginData")
    public Object[][] provideData() throws IOException {
        List<String[]> records = new ArrayList<>();
        String line;
        BufferedReader fileReader = new BufferedReader(new FileReader("swag_labs_users.csv"));

        while ((line = fileReader.readLine()) != null) {
            String[] fields = line.split(",");
            records.add(fields);
        }

        fileReader.close();

        Object[][] data = new Object[records.size()][2];
        for (int i = 0; i < records.size(); i++) {
            data[i] = records.get(i);
        }

        return data;
    }
}
