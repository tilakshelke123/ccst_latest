package ccstDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class Login1_SauceDemo_TestNG2 {
    WebDriver driver;
    WebDriverWait wait;
    public static final String USERNAME = "tilakshelke_fkzYAc";
    public static final String ACCESS_KEY = "yFxCnyxmuXcr4VA7vshE";
    public static final String BROWSERSTACK_URL = "https://" + USERNAME + ":" + ACCESS_KEY + 
    "@hub-cloud.browserstack.com/wd/hub";

    public void markTestStatus(String status, String reason, WebDriver driver)
    {
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("browserstack_executor: {\"action\":\"setSessionStatus\", \"arguments\": {\"status\":\""
    + status + "\", \"reason\": \"" + reason + "\"}}");
    }
    
    @Parameters({"os", "osVersion", "browser", "browserVersion"})
    @BeforeMethod
    public void setUp(String os, String osVersion, String browser, String browserVersion) throws MalformedURLException {
    	 
        DesiredCapabilities caps = new DesiredCapabilities();
        HashMap<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("os", os);
        bstackOptions.put("osVersion", osVersion);
        bstackOptions.put("projectName", "My TestNG Project");
        bstackOptions.put("buildName", "TestNG Build 1.0");
        bstackOptions.put("sessionName", "My Test Case");
        // attach BrowserStack options
        caps.setCapability("bstack:options", bstackOptions);
        
        driver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), caps);
    	
        driver = new ChromeDriver();
        
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        
        
  
    }
    
    
    
    
    
    
    

   // @Test(priority = 1)
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

  //  @Test(priority = 2)
    public void testNegativeLoginSauceDemo() {
        driver.get("https://www.saucedemo.com");

        try {
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce1");
        loginButton.click();

        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));

        // Assert that the error element is displayed
        Assert.assertTrue(errorElement.isDisplayed(), "Error message is not displayed.");  
        
        markTestStatus("passed", "User successfully logged in.", driver);
        }
        catch(Exception e) {
        	
        	 markTestStatus("failed", "User  logged in failed." + e.getMessage(), driver);
        	 throw e;
        }
    }
    
    @Test(priority = 3)
    public void CheckLogin() throws Exception {
        driver.get("https://www.saucedemo.com");

        try {
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        // click on product 
        By click = By.xpath("//a[@id='item_4_title_link']/descendant::div");
        WebElement clickInput = driver.findElement(click);
        Thread.sleep(2000);
        clickInput.click();
        
        By ret  = By.xpath("//button[@id='back-to-products']");
        WebElement retInput=driver.findElement(ret);
        Thread.sleep(2000);
        retInput.click();        
        
        wait.until(ExpectedConditions.urlContains("inventory.html"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory.html"), " back Successfull. URL: " + currentUrl);
        markTestStatus("passed", "user succesfully click on product", driver);
        }catch(Exception e) {
    	//  System.out.println( "Login failed URL: " +e.getMessage());
    	  markTestStatus("failed", "User failed to back " + e.getMessage(), driver);
     	 throw e;
      }
       
        }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }    
    
}
