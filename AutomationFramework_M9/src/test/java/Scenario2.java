import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Scenario2 {

	public static void main(String[] args) {
		       // Step:1 To Launch the Browser
				WebDriver driver=new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				driver.get("http://localhost:8888/");
				
				//Step:2 Passing valid credentials to login to application
				driver.findElement(By.name("user_name")).sendKeys("admin");
				driver.findElement(By.name("user_password")).sendKeys("password");
				driver.findElement(By.id("submitButton")).click();
				
				//Step:3 Navigate to contacts link
				driver.findElement(By.linkText("Organizations")).click();
				
				//Step:4 click on create contact look up image
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				//Step:5 Create contact with mandatory fields
				driver.findElement(By.name("accountname")).sendKeys("Qspiders");
				
				//Step:6 Save and verify
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				String OrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(OrgName.contains("Qspiders"))
					System.out.println(OrgName+ "------Passed");
				else
					System.out.println(OrgName + "------Failed");
				
				//Step:7 Logout 
				WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Actions action=new Actions(driver);
				action.moveToElement(logout).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				
				//Step:8 close Browser
				driver.quit();
				

	}

}