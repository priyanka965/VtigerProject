package practice;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DemoScript3 {

	public static void main(String[] args) {
		// Step:1 To Launch the Browser
		WebDriver driver=new ChromeDriver();
		
		//To maximize the browser
		driver.manage().window().maximize();
		
		//To give Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		//To go URL
		driver.get("http://localhost:8888/");
		
		//Step:2 Passing valid credentials to login to application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("password");
		driver.findElement(By.id("submitButton")).click();
		
		//Step:3 Navigate to contacts link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step:4 click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		
		Random r=new Random();
		int randomValue = r.nextInt(1000);
		
		
		//Step:5 Create contact with mandatory fields
		driver.findElement(By.name("accountname")).sendKeys("Qsp"+randomValue);
		WebElement industry = driver.findElement(By.name("industry"));
		
		Select dropdown=new Select(industry);
		dropdown.selectByValue("Chemicals");
		
		//Step:6 save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step:7 Logout 
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action=new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		
		//Step:8 close Browser
		driver.quit();
		

	}

}

