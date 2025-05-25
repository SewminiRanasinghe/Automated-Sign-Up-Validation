import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SignUpTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Utility reusable Methods
    public void click(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e) {
            System.out.println("Failed to click element: " + locator);
            Assert.fail("Click failed: " + e.getMessage());
        }
    }

    public void type(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Failed to type in element: " + locator);
            Assert.fail("Typing failed: " + e.getMessage());
        }
    }

    public void scrollIntoView(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            System.out.println("Scroll failed for element: " + locator);
        }
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
        click(By.xpath("//a[contains(@href, 'sign_in') and contains(translate(., 'SIGN IN', 'sign in'), 'sign in')]"));
    }

    @Test(priority = 2)
    public void clickSignUp() {
        By signUpLocator = By.xpath("//a[contains(@href, 'sign_up') and contains(translate(., 'SIGN UP', 'sign up'), 'sign up')]");
        scrollIntoView(signUpLocator);
        click(signUpLocator);
    }

    @Test(priority = 3)
    public void signUpWithEmail() {
        click(By.xpath("//a[text()='Signup with email']"));
    }

    @Test(priority = 4)
    public void fillForm() {
        try {
            type(By.id("fname"), "Example");
            type(By.id("lanme"), "Tester");
            type(By.id("email"), "example@test.com");
            type(By.id("mobileNum"), "0711234567");
            type(By.id("pw"), "StrongPass1234");
        } catch (Exception e) {
            System.out.println("Error filling the form: " + e.getMessage());
            Assert.fail("Form filling failed.");
        }
    }

    @Test(priority = 5)
    public void submitForm() {
        click(By.id("submit"));
    }

    @AfterClass
    public void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }
}
