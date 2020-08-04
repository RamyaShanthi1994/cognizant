package assignment;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FlipkartTest {
	static WebDriver driver;

	//This method will open the chrome browser and navigate to flipkart page
	@Test(priority = 0)
	public void getDriver() {
		String driverPath = "C:\\Users\\Ramya\\Desktop\\drivers\\";
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
	}

	
	//This method will login to the flipkart and assert whether user is logged in
	
	@Test(priority = 1)
	public void loginUser() throws Exception {
		FlipkartPage page = PageFactory.initElements(driver, FlipkartPage.class);
		page.enterUsername().enterPassword().clickLoginButton().waitMethod();
		assertTrue(page.myAccount.isDisplayed());
	}

	//This method will search for camera item and assert whether the result displayed as per the searched text
	
	@Test(priority = 2)
	public void searchItem() throws Exception {
		FlipkartPage page = PageFactory.initElements(driver, FlipkartPage.class);
		Thread.sleep(1000);
		page.enterSearchItem();
		page.waitMethod();
		System.out.println(page.resultDisplayed.getText());
		Assert.assertEquals(page.resultDisplayed.getText(), page.searchField.getAttribute("value"));
	}

	//this method will pick any random product and add the product to the cart
	
	@Test(priority = 3)
	public void selectSearchItem() throws Exception {
		FlipkartPage page = PageFactory.initElements(driver, FlipkartPage.class);
		String parentWindow = driver.getWindowHandle();
		page.waitMethod();
		int i = getRandom();
		ArrayList<String> productData = getProductDescription(i);
		String name=getProdName(i);
		driver.findElement(By.xpath("//div[@class='bhgxx2 col-12-12'][" + i + "]//a")).click();
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			driver.switchTo().window(window);
		}
		Assert.assertEquals(page.getProdNameCheckout().trim(), name);
		getProdDescInCheckoutPage(productData);
		page.clickBuyNowButton()
			.waitMethod();
		driver.close();
		driver.switchTo().window(parentWindow);
	}
	
	//This method will logout the user from the flipkart page

	@Test(priority = 4)
	public void logoutUser() throws Exception {
		FlipkartPage page = PageFactory.initElements(driver, FlipkartPage.class);
		page.waitMethod();
		Actions action = new Actions(driver);
		action.moveToElement(page.accountink);
		page.clickLogoutLink();
	}

	//the random number is generated so that the same product will not be selected everytime while execution
	
	public static int getRandom() {
		return (int) (Math.random() * ((10 - 1) + 1)) + 1;
	}

	// this method will take all the product description in product page
	
	public ArrayList<String> getProductDescription(int i) {
		List<WebElement> description = driver
				.findElements(By.xpath("//div[@class='bhgxx2 col-12-12'][" + i + "]//a/div[2]/div[1]/div[3]/ul/li"));

		ArrayList<String> prodDescription = new ArrayList<String>();
		for (WebElement desc : description) {
			System.out.println(desc.getText());
			prodDescription.add(desc.getText());
		}
		return prodDescription;
	}
	
	// this method will take all the product description in checkout page and compare the values

	public void getProdDescInCheckoutPage(ArrayList<String> prodDescription) {
		List<WebElement> prodDescInCheckout = driver.findElements(By.xpath("//div[text()='Highlights']/..//ul/li"));
		ArrayList<String> prodDescriptionCheckout = new ArrayList<String>();
		for (WebElement desc : prodDescInCheckout) {
			System.out.println(desc.getText());
			prodDescriptionCheckout.add(desc.getText());
		}
	}

	public String getProdName(int i) {
		String prodName=driver.findElement(By.xpath("//div[@class='bhgxx2 col-12-12'][" + i + "]//a/div[2]/div[1]/div[1]")).getText();
		try {
			String[] prodNames = prodName.split("\\(");
			prodName=prodNames[0].trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prodName;

	}
}
