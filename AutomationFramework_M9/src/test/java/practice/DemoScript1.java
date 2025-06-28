package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScript1 {

	public static void main(String[] args) {

    //Step 1: launch browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://localhost:8888/index.php?action=index&module=Home");
		
	//Step 2: Login to application with valid credentials
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
	//Step :3 navigate to contact link
		driver.findElement(By.linkText("Contacts")).click();
		
	//Step 4:Click on create contact lookup image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
	//Step 5: Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Singh");
		
	//Step 6:  Save and verify	
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 String lastName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		 
		 if(lastName.contains("Singh")) {
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
