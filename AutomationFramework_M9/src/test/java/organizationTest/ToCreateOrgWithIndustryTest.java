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
import genericUtility.WebDriverUtility;
@Listeners(genericUtility.ListenersImplementation.class)
public class ToCreateOrgWithIndustryTest  extends BaseClass {
	
	@Test(groups="Regression")
	public void toCreateOrgWithIndustry_001() throws EncryptedDocumentException, IOException{
		HomePage hp=new HomePage(driver);
		hp.getOrganizationLink().click();
		
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateOrganizationImg().click();
		
		ExcelFileUtility eutil=new ExcelFileUtility();
		Random r=new Random();
		int randomValue=r.nextInt(1000);
		String orgName = eutil.toReadDataFromExcelFile("Organization", 1, 2);
		
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		cop.getOrgName().sendKeys(orgName+randomValue);
		
		WebDriverUtility wutil=new WebDriverUtility();
		wutil.toHandleDropdown(cop.getIndustryDropDown(), "Education");//webElement,stringValue
		
		cop.getSaveButton().click();
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String nameofcreatedOrg = oip.getOrganizationInfoHeader().getText();
		
		Assert.assertTrue(nameofcreatedOrg.contains(orgName));
		
		
	}

}

