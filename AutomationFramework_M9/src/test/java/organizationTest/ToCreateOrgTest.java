package organizationTest;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import elementRepository.CreateOrganizationPage;
import elementRepository.HomePage;
import elementRepository.OrganizationInfoPage;
import elementRepository.OrganizationPage;
import genericUtility.BaseClass;
import genericUtility.ExcelFileUtility;
@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateOrgTest extends BaseClass{
	
	@Test
	public void toCreateOrg_001() throws EncryptedDocumentException, IOException
	{
		HomePage hp=new HomePage(driver);
		hp.getOrganizationLink().click();
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateOrganizationImg().click();
		
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		
		Random r=new Random();  // provides random numbers
		int randomValue=r.nextInt(1000);
		
		ExcelFileUtility eutil = new ExcelFileUtility();
		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2);
			
		
		cop.getOrgName().sendKeys(ORGNAME + randomValue);
		cop.getSaveButton().click();
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String orgname=oip.getOrganizationInfoHeader().getText();
		Assert.assertTrue(true);
	}

}
