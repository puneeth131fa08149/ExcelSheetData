package CustomerLoginPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Framework.DataDrivenFramework;

public class LoginDetails extends DataDrivenFramework{

	@FindBy(how=How.ID,using="Email") WebElement email;
	@FindBy(how=How.ID,using="Password") WebElement password;
	@FindBy(how=How.XPATH,using="//input[@class='button-1 login-button']") WebElement Login;
	@FindBy(how=How.XPATH,using="//a[contains(text(),'Logout')]") WebElement Logout;
	@FindBy(how = How.XPATH,using = "(//span[contains(text(),'Customers')])[1]")
	WebElement CustomersMenu;

	@FindBy(how = How.XPATH,using ="//a[@href='/Admin/Customer/List']//span[contains(text(),'Customers')]")
	WebElement customerMenuItem;

	@FindBy(how = How.XPATH,using ="//a[@class='btn bg-blue']")
	WebElement addButton;

	@FindBy(how= How.XPATH, using ="//input[@name='Email']")
	WebElement Email_text;

	@FindBy(how=How.XPATH,using ="//input[@id='Password']")
	WebElement Password_text;

	@FindBy(how= How.XPATH,using ="//input[@id='FirstName']")
	WebElement FirstName_text;

	@FindBy(how= How.XPATH,using ="//input[@name='LastName']")
	WebElement LastName_text;

	@FindBy(how= How.XPATH,using ="//input[@id='Gender_Male']")
	WebElement Gender_male;

	@FindBy(how= How.XPATH,using ="//input[@id='Gender_Female']")
	WebElement Gender_female;

	private @FindBy(how=How.XPATH, using ="//button[@name='save']")
	WebElement save_button;


	public LoginDetails() {
		PageFactory.initElements(driver, this);
	}
	public String validateTitle() {
		return driver.getTitle();
	}

	public void loginDetailsPage(String un ,String pwd) throws InterruptedException {
		
			clearAll(email);
			EnterText(email, un);
			clearAll(password);
			EnterText(password, pwd);
			
			ClickOnIt(Login);
			Thread.sleep(3000);
			if(driver.getTitle().contains("Customers / nopCommerce administration")) {
				
				System.out.println("Login Success");
			}else {
				System.out.println("Login Failed");
				driver.close();
				//driver.quit();
				report.flush();
			}
	}
	public void clickCustomersMenu()  {

		ClickOnIt(CustomersMenu);
	}

	public void clickCustomerMenuItem() {
		ClickOnIt(customerMenuItem);
	}

	public void clickOnAddbutton() {
		ClickOnIt(addButton);
	}

	public void Enter_email(String string) {
		EnterText(Email_text, string);
	}

	public void Enter_password(String string) {
		EnterText(Password_text, string);
	}

	public void Enter_Frist_Name(String string) {
		EnterText(FirstName_text, string);
	}

	public void Enter_Last_Name(String string) {
		EnterText(LastName_text, string);
	}

	public void select_gender(String gender) {
		if(gender.equalsIgnoreCase("Male"))
			ClickOnIt(Gender_male);
		else if(gender.equalsIgnoreCase("Female"))
			ClickOnIt(Gender_female);
	}
	
	public void click_save() {
		ClickOnIt(save_button);
	}

	public void LogOut() {
		ClickOnIt(Logout);
	}


}
