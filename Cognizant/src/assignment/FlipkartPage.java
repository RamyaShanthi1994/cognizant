package assignment;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlipkartPage {

	@FindBy(xpath = "//*[text()='Login']")
	private WebElement loginLink;
	
	@FindBy(xpath = "/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input")
	private WebElement userNameField;
	
	@FindBy(xpath = "//input[@type='password']")
	private WebElement passwordField;
	
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	private WebElement loginButton;
	
	@FindBy(xpath = "//input[@type='text']")
	public WebElement searchField;
	
	@FindBy(xpath = "//div[@class='bhgxx2 col-12-12']")
	private WebElement resultItems;
	
	@FindBy(xpath = "//button[text()='BUY NOW']")
	private WebElement buyNowButton;
	
	@FindBy(xpath = "//button[text()='NOTIFY ME']")
	private WebElement notifyButton;
	
	@FindBy (xpath = "//div[@class='bhgxx2 col-12-12'][5]//a/div[2]/div[1]/div[1]")
	private WebElement productName;
	
	@FindBy(xpath = "//div[@class='bhgxx2 col-12-12'][5]//a/div[2]/div[1]/div[3]/ul/li")
	private WebElement description;
	
	@FindBy(xpath = "//h1/span")
	private WebElement prodNameCheckout;
	
	@FindBy(xpath = "//div[text()='My Account']")
	public WebElement myAccount;
	
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[3]/div[2]/div[1]/div[2]/div[1]/div/div/span/span")
	public WebElement resultDisplayed;
	
	@FindBy(xpath = "//div[text()='Logout']/..")
	private WebElement logoutLink;
	
	@FindBy(xpath = "//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[3]")
	public WebElement accountink;
	
	public FlipkartPage login()
	{
		loginLink.click();
		return this;
	}
	
	public FlipkartPage enterUsername()
	{
		userNameField.sendKeys("7674068532");
		return this;
	}
	
	public FlipkartPage enterPassword()
	{
		passwordField.sendKeys("Windows@123");
		return this;
	}
	
	public FlipkartPage clickLoginButton()
	{
		loginButton.click();
		return this;
	}
	
	public FlipkartPage waitMethod() throws Exception
	{
		Thread.sleep(3000);
		return this;
	}
	
	public FlipkartPage enterSearchItem()
	{
		searchField.sendKeys("camera");
		searchField.sendKeys(Keys.ENTER);
		return this;
	}
	
	public FlipkartPage clickBuyNowButton()
	{
		try {
			buyNowButton.click();
		} catch (Exception e) {
			notifyButton.click();
		}
		return this;
	}
	
	public String getProductName()
	{
		String prodName=productName.getText();
		return prodName;
	}
	
	public String getProdNameCheckout()
	{
		String[] prodNameCheckoutData = prodNameCheckout.getText().split("\\(");
		System.out.println(prodNameCheckoutData[0]);
		return prodNameCheckoutData[0];
	}
	
	public FlipkartPage clickMyAccount()
	{
		accountink.click();
		return this;
	}
	
	public FlipkartPage clickLogoutLink()
	{
		logoutLink.click();
		return this;
	}
	
}


