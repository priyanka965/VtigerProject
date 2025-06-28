package contactTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.ContactInfoPage;
import elementRepository.ContactsPage;
import elementRepository.CreateContactPage;
import elementRepository.HomePage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateContactTest extends BaseClass {
     @Test
	//@Test(groups = "smoke")
	public void toCreateContact_001() throws EncryptedDocumentException, IOException {
		HomePage hp=new HomePage(driver);
		hp.getContactsLink().click();
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateContactIcon().click();
		
		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.getlastnameTextfield().sendKeys(LASTNAME);
		ccp.getSaveButton().click();
		Assert.fail(); //fail the test scripts
		ContactInfoPage cip=new ContactInfoPage(driver);
		String lastname=cip.getContactInfoHeader().getText();
		Assert.assertTrue(true);
		
	}
}