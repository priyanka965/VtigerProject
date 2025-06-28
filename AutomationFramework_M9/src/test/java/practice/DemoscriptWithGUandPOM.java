package practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import elementRepository.ContactInfoPage;
import elementRepository.ContactsPage;
import elementRepository.CreateContactPage;
import elementRepository.HomePage;
import elementRepository.LoginPage;
import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoscriptWithGUandPOM {

	public static void main(String[] args) throws IOException {
		PropertyFileUtility putil=new PropertyFileUtility();
		ExcelFileUtility eutil=new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		
		//To Read Data from propertyFile
				String URL = putil.toReadDataFromPropertyFile("url");
			 	String BROWSER = putil.toReadDataFromPropertyFile("browser");
			    String USERNAME = putil.toReadDataFromPropertyFile("username");
			    String PASSWORD = putil.toReadDataFromPropertyFile("password");
			    
			    //To Read data from ExcelFile
			   String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
			   
			   //Test Scripts
			   //Step 1: launch Browser
		       WebDriver driver=null;
		       if(BROWSER.equals("chrome")) {
		       	driver=new ChromeDriver();
		       }else if(BROWSER.equals("edge")){
		       	driver=new EdgeDriver();
		       }else if(BROWSER.equals("firefox")) {
		       	driver=new FirefoxDriver();
		       }
		       
		       wutil.toMaximize(driver);
		       wutil.waitForElement(driver);
		       driver.get(URL);
		       
		       //Step 2: Login to application with valid credentials
		       LoginPage lp= new LoginPage(driver);
		       lp.getUsernameTextfield().sendKeys(USERNAME);
		       lp.getPasswordTextfield().sendKeys(PASSWORD);
		       lp.getLoginButton().click();
		       
		    // Step :3 Navigate to contact link
		       HomePage hp=new HomePage(driver);
		       hp.getContactsLink().click();
		       
		    // Step 4:Click on create contact lookup image
		       ContactsPage cp=new ContactsPage(driver);
		       cp.getCreateContactIcon().click();
		       
		    // Step 5: Create contact with mandatory fields
		       CreateContactPage ccp=new CreateContactPage(driver);
		       ccp.getlastnameTextfield().sendKeys(LASTNAME);
		       
		       
		    // Step 6: Save and verify
		       ccp.getSaveButton().click();
		       ContactInfoPage cip=new ContactInfoPage(driver);
		       String lastname = cip.getContactInfoHeader().getText();
		       if(lastname.contains(LASTNAME)) {
		    	   System.out.println(lastname + "----passed");
				} else {
					System.out.println(lastname + "-----failed");
				
		       }
		       
		    // Step 7: logout of application
		       wutil.toMouseHover(driver, hp.getlogoutEle());
		       hp.getsignoutLink().click();
		       
		    // Step 8: close the browser
				driver.quit();
		       
		       
		       
		       
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   

	}

}
