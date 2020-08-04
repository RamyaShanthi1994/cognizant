package assignment;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FlipkartAssignment {
	public static String productName;
	
	public static void main(String[] args) throws Exception {
		WebDriver driver=getDriver();
		loginUser(driver);
		searchItem(driver);
		

	}
	
	public static WebDriver getDriver()
	{
		String driverPath = "C:\\Users\\Ramya\\Desktop\\drivers\\";
		System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		return driver;
	}
	
	public static void loginUser(WebDriver driver) throws Exception
	{
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("7674068532");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Windows@123");
		driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
	}
	
	public static void searchItem(WebDriver driver)
	{
		WebElement searchField=driver.findElement(By.xpath("//input[@type='text']"));
		searchField.sendKeys("camera");
		searchField.sendKeys(Keys.ENTER);
		String parentWindow=driver.getWindowHandle();
		int i=getRandom();
		productName=driver.findElement(By.xpath("//div[@class='bhgxx2 col-12-12']["+i+"]/div/div/div/a/div[2]/div[1]/div[1]")).getText();
		System.out.println(productName);
		driver.findElement(By.xpath("//div[@class='bhgxx2 col-12-12']["+i+"]//a")).click();
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			driver.switchTo().window(window);
		}
		driver.findElement(By.xpath("//button[text()='BUY NOW']")).click();
		
	}
	
	public static void validation(WebDriver driver)
	{
		productName=driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[2]/div[1]/div[2]/div[10]/div/div/div/a/div[3]/div[1]/div[1]")).getText();
	}

	public static int getRandom()
	{ 
		return (int) (Math.random()*10); 
	}
}
