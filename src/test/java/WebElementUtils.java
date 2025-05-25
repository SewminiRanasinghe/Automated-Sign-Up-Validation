import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WebElementUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor that receives WebDriver and WebDriverWait
    public WebElementUtils(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void click(By locator) {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
    }

    public void type(By locator, String text) {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.clear();
            element.sendKeys(text);

    }

    public void scrollIntoView(By locator) {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

    }
}
