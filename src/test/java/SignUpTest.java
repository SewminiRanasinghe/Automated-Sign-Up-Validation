import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class SignUpTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebElementUtils utils;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        utils = new WebElementUtils(driver, wait);
    }

    @Test(priority = 0)
    public void openHomePage() {
        try {
            driver.get("https://cog-stg.incubatelabs.com/");
            String actualURL = driver.getCurrentUrl();
            Assert.assertEquals(actualURL, "https://cog-stg.incubatelabs.com/", "Unexpected homepage URL!");
        } catch (Exception e) {
            System.out.println("Error opening the homepage: " + e.getMessage());
            Assert.fail("Failed to open home page or verify URL.");
        }
    }

    @Test(priority = 1)
    public void clickSignIn() {
        try{
            utils.click(By.xpath("//a[contains(@href, 'sign_in') and contains(translate(., 'SIGN IN', 'sign in'), 'sign in')]"));
        }catch (Exception e) {
            System.out.println("Failed to click element");
            Assert.fail("Click failed: " + e.getMessage());
        }

    }

    @Test(priority = 2)
    public void clickSignUp() {
        try{
            By signUpLocator = By.xpath("//a[contains(@href, 'sign_up') and contains(translate(., 'SIGN UP', 'sign up'), 'sign up')]");
            utils.scrollIntoView(signUpLocator);
            utils.click(signUpLocator);
        }catch (Exception e) {
            System.out.println("Failed to click element");
            Assert.fail("Click failed: " + e.getMessage());
        }

    }

    @Test(priority = 3)
    public void signUpWithEmail() {
        try {
            utils.click(By.xpath("//a[text()='Signup with email']"));
        }catch (Exception e) {
            System.out.println("Failed to click element");
            Assert.fail("Click failed: " + e.getMessage());
        }

    }

    @Test(priority = 4)
    public void fillForm() {
        try {
            utils.type(By.id("fname"), "Example");
            utils.type(By.id("lanme"), "Tester"); // Check if "lanme" is a typo. Should it be "lname"?
            utils.type(By.id("email"), "example@test.com");
            utils.type(By.id("mobileNum"), "0711234567");
            utils.type(By.id("pw"), "StrongPass1234");
        } catch (Exception e) {
            System.out.println("Error filling the form: " + e.getMessage());
            Assert.fail("Form filling failed.");
        }
    }

    @Test(priority = 5)
    public void submitForm() {
        try{
            utils.click(By.id("submit"));
        }catch (Exception e) {
            System.out.println("Failed to click element ");
            Assert.fail("Click failed: " + e.getMessage());
        }

    }

    @AfterClass
    public void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }
}
