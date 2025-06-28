
package practice;



import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScript5 {

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

		// step 3:Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();

		// step 4:Click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 5:create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys("Priya");

		// step 6:select the organization from organization look up image
		driver.findElement(By.xpath("(//img[@title='Select'])[1]")).click();

		String parentid = driver.getWindowHandle();
		Set<String> ids = driver.getWindowHandles();
		ids.remove(parentid);
		for (String id : ids)
			driver.switchTo().window(id);
		driver.findElement(By.linkText("ABCD")).click();
		driver.switchTo().window(parentid);

		// step 8:save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (lastname.contains("Abhi"))
			System.out.println(lastname + "---Passed");
		else
			System.out.println(lastname + "---Failed");

		// step 9:logout of application
		WebElement ele = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(ele).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// step 10:close browser
		driver.quit();

	}

}
