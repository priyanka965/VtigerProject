package practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelFileUtility;
import genericUtility.PropertyFileUtility;
import genericUtility.WebDriverUtility;

public class DemoScriptWithDDTAndWU {

	public static void main(String[] args) throws IOException {
		PropertyFileUtility putil = new PropertyFileUtility();
		ExcelFileUtility eutil = new ExcelFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		// To Read Data from propertyFile
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		// To Read data from ExcelFile
		String LASTNAME = eutil.toReadDataFromExcelFile("contacts", 1, 2);

		// Step 1: launch Browser
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		wutil.toMaximize(driver);// WebDriverUtilty
		wutil.waitForElement(driver);
		driver.get(URL);

		// Step 2: Login to application with valid credentials

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		// Step :3 navigate to contact link
		driver.findElement(By.linkText("Contacts")).click();

		// Step 4:Click on create contact lookup image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();

		// Step 5: Create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);

		// Step 6: Save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		if (lastName.contains(LASTNAME)) {
			System.out.println(lastName + "----passed");
		} else {
			System.out.println(lastName + "-----failed");
		}

		// Step 7: logout of application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMouseHover(driver, logoutEle);
		driver.findElement(By.linkText("Sign Out")).click();

		// Step 8: close the browser
		driver.quit();

	}

}
