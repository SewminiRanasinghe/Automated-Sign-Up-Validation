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
        try{
            driver.get("https://cog-stg.incubatelabs.com/");
            //Verify the correct page has loaded
            String homePageURL = driver.getCurrentUrl();
            Assert.assertEquals(homePageURL, "https://cog-stg.incubatelabs.com/");
        }catch (Exception e) {
            System.out.println("Error opening the homepage: " + e.getMessage());
            Assert.fail("Failed to open home page or verify URL.");
        }

    }
    // Click Sign-In-> Sign-Up -> Sign-Up with Email
    //Click on sign in
    @Test(priority = 1)
    public void clickSignIn(){
        try{
            WebElement SignIn =  wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'sign_in') and contains(translate(., 'SIGN IN', 'sign in'), 'sign in')]")));
            SignIn.click();
        }catch (TimeoutException e) {
            System.out.println("Sign-In element not clickable within timeout.");
            Assert.fail("Sign-In button was not clickable in time.");
        }

    }

    @Test(priority = 2)
    public void clickSignUp(){
        try{
            //click on sign up
            WebElement SignUp =  wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'sign_up') and contains(translate(., 'SIGN UP', 'sign up'), 'sign up')]")));
            // Scroll into view if needed
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", SignUp);
            SignUp.click();
        }catch (TimeoutException e) {
            System.out.println("Sign-Up element not clickable within timeout.");
            Assert.fail("Sign-Up button was not clickable in time.");
        }
    }

    //click on Sign-Up with Email
    @Test(priority = 3)
    public void signUpWithEmail(){
        try{
            WebElement SignUpWithEmail =  wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Signup with email']")));
            SignUpWithEmail.click();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            Assert.fail("Unexpected error");
        }

    }

    //Fill form
    @Test(priority = 4)
    public void fillForm() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.name("first_name"))).sendKeys("Example");
            driver.findElement(By.name("last_name")).sendKeys("Tester");
            driver.findElement(By.id("email")).sendKeys("example@test.com");
            driver.findElement(By.id("mobileNum")).sendKeys("0711234567");
            driver.findElement(By.id("pw")).sendKeys("StrongPass1234");
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Error filling the form: " + e.getMessage());
            Assert.fail("Form filling failed due to missing element or timeout.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            Assert.fail("Unexpected error during form fill.");
        }
    }

    // Submit form
    @Test(priority = 5)
    public void submitForm(){
        try{
            WebElement Submit =  wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("submit")));
            Submit.click();
        }catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Error filling the form: " + e.getMessage());
            Assert.fail("Form filling failed due to missing element or timeout.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            Assert.fail("Unexpected error during form submission.");
        }

    }

    @AfterClass
    public void cleanup() {
        driver.quit();
    }

}
