package elementRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
	
	//constructor
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="lastname")
	private WebElement lastnameTextfield;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(xpath = "(//img[@title='Select'])[1]")
	private WebElement orgImg;

	public WebElement getlastnameTextfield() {
		return lastnameTextfield;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getOrgImg() {
		return orgImg;
	}

	
	
	

}
