package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
	
	@FindBy(name="username")
	public WebElement uid;

	@FindBy(name="password")
	public WebElement pwd;
	
	@FindBy(xpath="//*[@type='submit']")
	public WebElement instaclick;
	
	@FindBy(xpath="//*[contains(text(),'check your username and try again.')]")
	public WebElement uidinvalid;
	
	@FindBy(xpath="//*[contains(text(),'Sorry, your password was incorrect.')]")
	public WebElement pwdinvalid;
	
	@FindBy(xpath="//*[contains(text(),'Sorry, your password was incorrect.')]")
	public WebElement pwdblanker;
	
	@FindBy(xpath="//*[text()='or']")
	public WebElement uidvalid;
	
	public Loginpage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public void userid(String x)
	{
		uid.sendKeys(x);
	}
	public void Passwd(String y)
	{
		pwd.sendKeys(y);
	}
	public void loginclick()
	{
		instaclick.submit();
	}
}
