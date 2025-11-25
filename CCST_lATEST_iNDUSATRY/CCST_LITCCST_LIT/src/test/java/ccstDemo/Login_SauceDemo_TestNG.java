package ccstDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class Login_SauceDemo_TestNG {
    WebDriver driver;
    WebDriverWait wait;
    

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testPositiveLoginSauceDemo() {
        driver.get("https://www.saucedemo.com");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        // Verify login by checking the URL
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), "Login failed. URL: " + currentUrl);
    }

    @Test(priority = 2)
    public void testNegativeLoginSauceDemo() {
        driver.get("https://www.saucedemo.com");

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce1");
        loginButton.click();

        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));

        // Assert that the error element is displayed
        Assert.assertTrue(errorElement.isDisplayed(), "Error message is not displayed.");               
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }    
    
}
