package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {
	 public WebDriver driver;
	   
	   @FindBy(xpath="//*[text()='Search']")
	   public WebElement search;
	   
	   @FindBy(xpath="//*[text()='Not Now']")
	   public WebElement alert_cancel;
	   
	   public Homepage(WebDriver driver)
	   {
		   PageFactory.initElements(driver,this);
	   }
	   
	   public void cancel_btn()
	   {
		   alert_cancel.click();
	   }

}
