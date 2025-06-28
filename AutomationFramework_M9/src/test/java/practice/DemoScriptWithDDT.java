package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;


public class DemoScriptWithDDT {

	public static void main(String[] args) throws IOException {
        //Read Data from Property File
		FileInputStream pfis = new FileInputStream("./src/test/resources/commonData.properties");
		Properties prop=new Properties();
		prop.load(pfis);
		
	    String BROWSER = prop.getProperty("browser");
        String URL = prop.getProperty("url");
        String USERNAME = prop.getProperty("username");
        String PASSWORD = prop.getProperty("password");
        
        //Read Data from Excel File
        FileInputStream efis=new FileInputStream("./src/test/resources/testData.xlsx");
        Workbook wb=WorkbookFactory.create(efis);
        String LASTNAME = wb.getSheet("Contacts").getRow(1).getCell(2).toString();
        
        
        //Step 1: launch Browser
        WebDriver driver=null;
        if(BROWSER.equals("chrome")) {
        	driver=new ChromeDriver();
        }else if(BROWSER.equals("edge")){
        	driver=new EdgeDriver();
        }else if(BROWSER.equals("firefox")) {
        	driver=new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(URL);
        
        
      //Step 2: Login to application with valid credentials
		
      		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
      		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
      		driver.findElement(By.id("submitButton")).click();
      		
      	//Step :3 navigate to contact link
      		driver.findElement(By.linkText("Contacts")).click();
      		
      	//Step 4:Click on create contact lookup image
      		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
      		
      	//Step 5: Create contact with mandatory fields
      		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
      		
      	//Step 6:  Save and verify	
      		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
      		 String lastName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
      		 
      		 if(lastName.contains(LASTNAME)) {
      			 System.out.println(lastName+"----passed");
      		 }
      		 else {
      			 System.out.println(lastName+"-----failed");
      		 }
      		 
      	//Step 7: logout of application
      		  WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
      		  Actions action=new Actions(driver);
      		  action.moveToElement(logoutEle).perform();
      		  driver.findElement(By.linkText("Sign Out")).click();
      		  
      	//Step 8: close the browser
      		  driver.quit();
      		
    
    
 
	}

}
