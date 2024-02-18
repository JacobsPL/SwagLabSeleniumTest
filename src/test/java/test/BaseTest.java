package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LoginPage;

import java.time.Duration;



public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod
    public void Setup() {
        System.setProperty("webdriver.geco.driver", "C:\\Users\\jakub\\OneDrive\\Pulpit\\Programowanko\\Selenium\\gecodriver-v0.33.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.saucedemo.com");
        initializeLoginPage();
    }

    private void initializeLoginPage() {

        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
