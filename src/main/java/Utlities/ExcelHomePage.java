package Utlities;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import CustomerLoginPage.HomePageDetailes;
import CustomerLoginPage.LoginDetails;
import ExcelSheet.SheetDetails;
import Framework.DataDrivenFramework;

public class ExcelHomePage extends DataDrivenFramework{
	private static HomePageDetailes hp;

	public static  void CustomerHomePage() throws EncryptedDocumentException, IOException {
		hp = PageFactory.initElements(driver, HomePageDetailes.class);
		xl = new SheetDetails("F:\\Selenium\\MavenProject\\ExcelSheetData\\src\\main\\java\\resourseExcelSheet\\ExcelSheet.xlsx");
		int row=xl.rowCount("HomePage");
		int col=xl.colCount("HomePage");
		System.out.println("no.of rows:"+row+" "+"no.of columns"+col);
		for (int i = 0; i < row; i++) {
			hp.clickCustomersMenu();
			hp.clickCustomerMenuItem();
			hp.clickOnAddbutton();
			String EmailId=xl.getCellData("HomePage", i, 0);
			String password=xl.getCellData("HomePage", i,1);
			String firstName=xl.getCellData("HomePage", i, 2);
			String LastName=xl.getCellData("HomePage", i, 3);
			String Gender=xl.getCellData("HomePage", i, 4);
			hp.Enter_email(EmailId);
			hp.Enter_password(password);
			hp.Enter_Frist_Name(firstName);
			hp.Enter_Last_Name(LastName);
			hp.select_gender(Gender);
			if (hp.verifyHomePageTitle().contains("Add a new customer / nopCommerce administration")) {
				Reporter.log("Login success",true);
			}else {
			}
			report.flush();
		}
	}
}
