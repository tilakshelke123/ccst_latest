package comcart;

import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptException;
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

public class CartCheck {
	WebDriver driver;
	
	public static final String USERNAME = "tilakshelke_fkzYAc";
	public static final String ACCESS_KEY = "yFxCnyxmuXcr4VA7vshE";
	public static final String BROWSERSTACK_URL = "https://" + USERNAME + ":" + ACCESS_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";
	WebDriverWait wait;

	public void markTestStatus(String status, String reason, WebDriver driver)
	{JavascriptExecutor jse = (JavascriptExecutor) driver;
	jse.executeScript("browserstack_executor:{\"action\":\"setSessionStatus\", \"arguments\": {\"status\":\""+ status + "\", \"reason\": \"" + reason + "\"}}");
	}

	@Parameters({ "os", "osVersion", "browser", "browserVersion" })
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

	@Test(priority = 1)
	public void checkAddcart() {
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

			// addTo cart
			By addTocart = By.xpath("//button[@id='add-to-cart']");
			WebElement addTocartCheck = driver.findElement(addTocart);
			Thread.sleep(2000);
			addTocartCheck.click();
			Thread.sleep(2000);
			// click on Cart
			By cart = By.xpath("//a[@class='shopping_cart_link']");
			WebElement cartCheck = driver.findElement(cart);
			Thread.sleep(3000);
			cartCheck.click();

			Thread.sleep(5000);
			// verfied product in the cart or not
//			System.out.println(driver.getCurrentUrl());
			WebElement store = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remove-sauce-labs-backpack")));
//
			Assert.assertTrue(store.isDisplayed(), " product present succesfully in the cart  " + store);
//			Thread.sleep(5000);
			markTestStatus(" pass ", "Add to Cart ", driver);

		} 
		catch (Exception e) {
			markTestStatus(" failed ", "Add to Cart Failed " + e.getMessage(), driver);
			
		}

	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}