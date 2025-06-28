package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScript2 {
	

		public static void main(String[] args) {
			// To Launch the Browser
					WebDriver driver=new ChromeDriver();
					
					//To maximize the browser
					driver.manage().window().maximize();
					
					//To give Implicit Wait
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
					
					//To go URL
					driver.get("http://localhost:8888/");
					
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
					driver.findElement(By.name("accountname")).sendKeys("JP Morgan"+randomValue);
					
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


