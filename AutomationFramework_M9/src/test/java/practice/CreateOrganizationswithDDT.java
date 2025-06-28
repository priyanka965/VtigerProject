package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganizationswithDDT {
	
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
        String ORGNAME = wb.getSheet("Organization").getRow(1).getCell(2).toString();
        
        
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
        
      //Passing valid credentials to login to application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		//Navigate to contacts link
		driver.findElement(By.linkText("Organizations")).click();
		
		//click on create Organization look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//RandomNumbers
		Random r=new Random();
		int randomValue = r.nextInt(1000);
		
		
		//Create Oragnisation with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+randomValue);
		
		//Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String OrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(OrgName.contains("JP Morgan"+randomValue))
			System.out.println(OrgName+ "------Passed");
		else
			System.out.println(OrgName + "------Failed");
		
		//Logout 
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action=new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		//close Browser
		driver.quit();
		

}


}
