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

    @Test(priority = 0)
    public void openHomePage() {
        driver.get("https://cog-stg.incubatelabs.com/");
        //Verify the correct page has loaded
        String homePageURL = driver.getCurrentUrl();
        Assert.assertEquals(homePageURL, "https://cog-stg.incubatelabs.com/");
    }
    // Click Sign-In-> Sign-Up -> Sign-Up with Email
    @Test(priority = 1)
    public void clickSignIn(){
        //Click on sign in
        WebElement SignIn =  wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'sign_in') and contains(translate(., 'SIGN IN', 'sign in'), 'sign in')]")));
        SignIn.click();
    }

    @Test(priority = 2)
    public void clickSignUp(){
        //click on sign up
        WebElement SignUp =  wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'sign_up') and contains(translate(., 'SIGN UP', 'sign up'), 'sign up')]")));
        // Scroll into view if needed
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", SignUp);
        SignUp.click();
    }

    @Test(priority = 3)
    public void signUpWithEmail(){
        //click on Sign-Up with Email
        WebElement SignUpWithEmail =  wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Signup with email']")));
        SignUpWithEmail.click();
    }

    @Test(priority = 4)
    public void fillForm() {
        // Fill form
        wait.until(ExpectedConditions.elementToBeClickable(By.name("first_name"))).sendKeys("Example");
        driver.findElement(By.name("last_name")).sendKeys("Ranasinghe");
        driver.findElement(By.id("email")).sendKeys("sakura@test.com");
        driver.findElement(By.id("mobileNum")).sendKeys("0711234567");
        driver.findElement(By.id("pw")).sendKeys("StrongPass1234");
    }

    @Test(priority = 5)
    public void submitForm(){
        // Submit form
        WebElement Submit =  wait.until(
                ExpectedConditions.elementToBeClickable(By.id("submit")));
        Submit.click();
    }

    @AfterClass
    public void cleanup() {
        driver.quit();
    }

}
