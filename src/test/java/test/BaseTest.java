package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.ItemListPage;
import page.ItemPage;
import page.LoginPage;

import java.time.Duration;



public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ItemListPage itemListPage;
    protected ItemPage itemPage;

    @BeforeMethod
    public void Setup() {
        System.setProperty("webdriver.geco.driver", "C:\\Users\\jakub\\OneDrive\\Pulpit\\Programowanko\\Selenium\\gecodriver-v0.33.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.saucedemo.com");

        loginPage = new LoginPage(driver);
        itemPage = new ItemPage(driver);
        itemListPage = new ItemListPage(driver);
    }

    public void logInAsStandardUser(){
        loginPage.typeUsername("standard_user")
                .typePassword("secret_sauce")
                .clickLoginButton();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
