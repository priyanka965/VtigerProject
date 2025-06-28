package practice;



import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DemoScript4 {
	public static void main(String[] args) {
	
	        // step 1: Launch Browser
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

			// step 2:Login to application with valid credentials
			driver.get("http://localhost:8888/");
			driver.findElement(By.name("user_name")).sendKeys("admin");
			driver.findElement(By.name("user_password")).sendKeys("password");
			driver.findElement(By.id("submitButton")).click();

			// step 3:Navigate to Oraganizations link
			driver.findElement(By.linkText("Organizations")).click();

			// step 4:Click on create Organization look up image
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			
			Random r=new Random();
			int randomValue = r.nextInt(1000);

			// step 5:create Organization with mandatory fields
			driver.findElement(By.name("accountname")).sendKeys("ABCD2"+randomValue);
			
			//step 6:select "energy" in the industry dropdown
			WebElement industry = driver.findElement(By.name("industry"));
			Select st = new Select(industry);
			st.selectByValue("Energy");
			
			// step 7:select "customer" in the industry dropdown
			WebElement industry1 = driver.findElement(By.name("accounttype"));
			Select sa = new Select(industry1);
			sa.selectByValue("Customer");
			
			// step 6:save and verify
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if (lastname.contains("ABCD2"))
				System.out.println(lastname + "---Passed");
			else
				System.out.println(lastname + "---Failed");

			// step 7:logout of application
			WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			Actions action = new Actions(driver);
			action.moveToElement(ele).perform();
			driver.findElement(By.linkText("Sign Out")).click();

			// step 8:close browser
			driver.quit();

	
	}
}

