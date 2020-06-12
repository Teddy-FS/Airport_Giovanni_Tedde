import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

public class GettingStarted {
    @Test
    public void testGoogleSearch() throws InterruptedException {
        // Optional. If not specified, WebDriver searches the PATH for chromedriver.
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080");
        Thread.sleep(5000);  // Let the user actually see something!
        WebElement button = driver.findElement(By.id("fly1231ASAP"));
        button.click();
        Thread.sleep(1000);  // Let it load
        WebElement sendForm = driver.findElement(By.id("flyPlaneAirport"));
        sendForm.sendKeys("Somewhere");
        button = driver.findElement(By.id("flyButton"));
        button.click();
        Thread.sleep(5000);  // Let the user actually see something!
        driver.quit();
    }
}