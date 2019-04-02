package glue;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import pages.Homepage;
import pages.Loginpage;

public class Instagluee {
	public WebDriver driver;
	public WebDriverWait wait;
	public Homepage hp;
	public Loginpage lp;
	public Scenario s;
	public Properties p;
	
	@Before
	public void method1(Scenario s)throws Exception
	{
		this.s=s;
		File f=new File("D:\\WorkSpace\\InstaTest\\src\\test\\resources\\repository\\file1.properties");
		FileReader fr=new FileReader(f);
		p=new Properties();
		p.load(fr);
	}
	
	@Given("^launch site$")
	public void method2()
	{
		System.setProperty("webdriver.chrome.driver",p.getProperty("cdpath"));
		driver=new ChromeDriver();
		hp=new Homepage(driver);
		lp=new Loginpage(driver);
		driver.get(p.getProperty("url"));
		wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(lp.uid));
		driver.manage().window().maximize();
	}
	
	@When("^enter userid as \"(.*)\"$")
	public void method3(String x)
	{
		wait.until(ExpectedConditions.visibilityOf(lp.uid));
		lp.userid(x);
	}
	
	@And("^enter password as \"(.*)\"$")
	public void method4(String x)
	{
		wait.until(ExpectedConditions.visibilityOf(lp.pwd));
		lp.Passwd(x);
	}
	
	@And("^click login$")
	public void method5()
	{
		wait.until(ExpectedConditions.visibilityOf(lp.instaclick));
		lp.loginclick();
	}
	
	@Then("^validate output for criteria \"(.*)\" for \"(.*)\" and \"(.*)\"$")
	public void method6(String c,String x,String y)throws Exception
	{
		Thread.sleep(5000);
	    try 
	    {
	    	if(c.equals("all_valid") && hp.search.isDisplayed())
	    	{
	    		
	    		wait.until(ExpectedConditions.visibilityOf(hp.alert_cancel));
	    	    hp.cancel_btn();
	    		s.write("valid userid and password test passed");
	    	}
	    	else if(c.equals("uid_invalid") && lp.uidinvalid.isDisplayed())
	    	{
	    		s.write("invalid userid test passed");
	    	}
	    	else if(c.equals("pwd_invalid") && lp.pwdinvalid.isDisplayed())
	    	{
	    		s.write("invalid pwd test passed");
	    	}
	    	else if(y.length()==0 && c.equals("blank_pwd") && lp.pwdblanker.isDisplayed())
	    	{
	    		s.write("blank pwd test passed");
	    	}
	    	else
	    	{
	    		byte[] ss=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	    		s.embed(ss,"login test failed");
	    		Assert.fail();
	    	}
	    }
	    catch(Exception ex)
	    {
	    	byte[] ss=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    		s.embed(ss,ex.getMessage());
    		Assert.fail();
	    }
	}
	
	@And("^close site$")
	public void method7()
	{
		driver.quit();
	}
}

