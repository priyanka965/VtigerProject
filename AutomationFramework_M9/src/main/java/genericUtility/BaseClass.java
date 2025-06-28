package genericUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import elementRepository.HomePage;
import elementRepository.LoginPage;

public class BaseClass {
	
	PropertyFileUtility putil = new PropertyFileUtility();
	ExcelFileUtility eutil = new ExcelFileUtility();
	WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sDriver;
	
	@BeforeSuite
	public void beforeSuiteConfig()
	{
		System.out.println("---DataBase Connection Established---");
	}
	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass()
	public void beforeClassConfig(/*String BROWSER*/) throws IOException {
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String URL = putil.toReadDataFromPropertyFile("url");
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		sDriver=driver; //Listeners
		
		System.out.println("Browser got Launched");
		wutil.toMaximize(driver);
		System.out.println("Browser got Maximized");
		wutil.waitForElement(driver);
		driver.get(URL);
	}
	
	@BeforeMethod
	public void beforeMethodConfig() throws IOException {
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.getUsernameTextfield().sendKeys(USERNAME);
		lp.getPasswordTextfield().sendKeys(PASSWORD);
		lp.getLoginButton().click();
		System.out.println("Logged in to Vtiger");
	}

	@AfterMethod
	public void afterMethodConfig()
	{
		HomePage hp=new HomePage(driver);
		wutil.toMouseHover(driver, hp.getlogoutEle());
		hp.getsignoutLink().click();
		System.out.println("Logged out from Vtiger");
	}
	
	@AfterClass
	public void afterClassConfig()
	{
		System.out.println("Browser got closed");
		driver.quit();
	}
	
	@AfterSuite
	public void afterSuiteConfig()
	{
		System.out.println("---DataBase Connection Disconnected---");
	}

}