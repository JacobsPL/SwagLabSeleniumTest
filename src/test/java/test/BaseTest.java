package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.BasePage;
import page.ItemListPage;
import page.ItemPage;
import page.LoginPage;

import java.time.Duration;
import java.util.Map;
import java.util.Objects;


public class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ItemListPage itemListPage;
    protected ItemPage itemPage;
    private final String USER_LOGIN = "standard_user";
    private final String USER_PASSWORD = "secret_sauce";
    private final String TEST_SITE = "https://www.saucedemo.com";

    public BasePage basePage;


    @BeforeMethod
    public void Setup() {

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac")) {
            System.setProperty("webdriver.gecko.driver", "./GecoDriver/geckodriver");
        }
        else if (os.contains("win")) {
            System.setProperty("webdriver.geco.driver","./GecoDriver/geckodriver.exe");
        } else {
            throw new IllegalStateException("Unsupported operating system: " + os);
        }

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(TEST_SITE);

        basePage = new BasePage(driver);
        loginPage = new LoginPage(driver);
        itemPage = new ItemPage(driver);
        itemListPage = new ItemListPage(driver);
    }

    public void logInAsStandardUser(){
        loginPage.typeUsername(USER_LOGIN)
                .typePassword(USER_PASSWORD)
                .clickLoginButton();
    }

    public static boolean mapsAreEqual(Map<String, Double> map1, Map<String, Double> map2) {
        // Check if the maps have the same size
        if (map1.size() != map2.size()) {
            return false;
        }

        for (Map.Entry<String, Double> entry : map1.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();

            // Check if map2 contains the same key and value
            if (!map2.containsKey(key) || !Objects.equals(map2.get(key), value)) {
                return false;
            }
        }
        return true;
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
