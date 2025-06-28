package contactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.ContactInfoPage;
import elementRepository.ContactsPage;
import elementRepository.CreateContactPage;
import elementRepository.CreateContactWithOrg;
import elementRepository.HomePage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
import genericUtility.WebDriverUtility;

@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactWithOrgTest extends BaseClass{
	
	@Test(groups="smoke")
	public void toCreateContactWithOrg_002() throws EncryptedDocumentException, IOException {
	HomePage hp=new HomePage(driver);
	hp.getContactsLink().click();
	ContactsPage cp=new ContactsPage(driver);
	cp.getCreateContactIcon().click();
	
	ExcelFileUtility eutil = new ExcelFileUtility();
	String LASTNAME = eutil.toReadDataFromExcelFile("contacts", 1, 2);
	CreateContactPage ccp=new CreateContactPage(driver);
	ccp.getlastnameTextfield().sendKeys(LASTNAME);
	ccp.getOrgImg().click();
	
	WebDriverUtility wutil=new WebDriverUtility();
	wutil.toSwitchWindow(driver, "Accounts");//To Switch to other Window
	
	//HardCoded
	//driver.findElement(By.xpath("//a[text()='TCS']")).click();

	/*
	 * String parentid = driver.getWindowHandle(); Set<String> ids =
	 * driver.getWindowHandles(); ids.remove(parentid); for (String id : ids)
	 * driver.switchTo().window(id);
	*/
	CreateContactWithOrg cco=new CreateContactWithOrg(driver);// to switch back to main window
	cco.getOrgName().click();
	
	wutil.toSwitchWindow(driver, "Contacts");
	//driver.switchTo().window(parentid);
	
	ccp.getSaveButton().click();
	ContactInfoPage cip=new ContactInfoPage(driver);
	String lastname=cip.getContactInfoHeader().getText();
	/*
	 * if(lastname.contains(LASTNAME)) System.out.println(lastname + "---Passed");
	 * else System.out.println(lastname + "---Failed");
	 */
	Assert.assertTrue(lastname.contains(LASTNAME));
		
	}

}
