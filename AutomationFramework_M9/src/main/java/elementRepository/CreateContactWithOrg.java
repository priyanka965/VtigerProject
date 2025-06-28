package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactWithOrg {
	public CreateContactWithOrg(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Qspiderss")
	private WebElement orgName;

	public WebElement getOrgName() {
		return orgName;
	}
	

	}