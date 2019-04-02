package glue;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import pages.Homepage;
import pages.Loginpage;

public class Instaaglue {

	public WebDriver driver;
	public WebDriverWait wait;
	public Homepage hp;
	public Loginpage lp;
	public Scenario s;
	public Properties p;
	
	@Before
	public void method1(Scenario s) throws Exception
	{
		this.s=s;
		FileInputStream fi = new FileInputStream("D:\\WorkSpace\\InstaTest\\src\\test\\resources\\repository\\file1.properties");
		p=new Properties();
		p.load(fi);
	}
	
	@Given("^launch a site$") 
	public void launch_site()
	{
		System.setProperty("webdriver.chrome.driver", p.getProperty("cdpath"));
		driver= new ChromeDriver();
		hp=new Homepage(driver);
		lp=new Loginpage(driver);
		driver.get(p.getProperty("url"));
		wait= new WebDriverWait(driver,20);
		driver.manage().window().maximize();
	}
	
	@When("^user enters userid and password$")
	public void user_enters_userid_and_password(DataTable dt) throws Exception
	{
		List<Map<String,String>> data = dt.asMaps(String.class,String.class);
		for(int i=0; i<data.size(); i++)
		{
			String uid=data.get(i).get("username");
			String pwd=data.get(i).get("password");
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
			driver.findElement(By.name("username")).sendKeys(uid);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
			driver.findElement(By.name("password")).sendKeys(pwd);
		}
	}
	
	@And("^click login button$")
	public void click_login_button()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type='submit']")));
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Not Now']")));
	    driver.findElement(By.xpath("//*[text()='Not Now']")).click();
	}
	

	
	
	@Then("^message displayed Login Successfully$")
	public void message_displayed_login_successfully()
	{
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Search']")));
	     try
		{  
		  
			Thread.sleep(3000);
			 if(driver.findElement(By.xpath("//*[text()='Search']")).isDisplayed())
			{
				s.write("Homepage is dispalyed");
			}
			 else  if(driver.findElement(By.xpath("//*[contains(text(),'Sorry, your password was incorrect.')]")). isDisplayed())
			{
				s.write("PWD invalid value test passed");
			}
			else if(driver.findElement(By.xpath("//*[contains(text(),'check your username and try again.')]")).isDisplayed())
	         {
				s.write("UID invalid value test passed");
	         }
			else if(driver.findElement(By.xpath("//*[contains(text(),'Sorry, your password was incorrect.')]")). isDisplayed())
			{
				s.write("PWD blank value test passed");
			}
		    else {
				byte [] b=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
				s.embed(b,"Insta Login  test was  failed");
			}
						
			
		}
		catch(Exception ex)
		{
			s.write(ex.getMessage());

			
		}
	     
	    
	}
	
	
	@When("^close a site$")
	public void close_site( )
	{
		driver.quit();
	}
}
