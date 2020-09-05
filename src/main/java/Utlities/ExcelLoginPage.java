package Utlities;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import CustomerLoginPage.LoginDetails;
import ExcelSheet.SheetDetails;
import Framework.DataDrivenFramework;

public class ExcelLoginPage extends DataDrivenFramework{
	
	public static void CustomerLoginPageDetails() throws Throwable {
		//call login page
		LoginDetails login=PageFactory.initElements(driver, LoginDetails.class);
		xl = new SheetDetails(System.getProperty("user.dir")+"\\src\\main\\java\\resourseExcelSheet\\ExcelSheet.xlsx");
		int row=xl.rowCount("Login");
		int col=xl.colCount("Login");
		System.out.println("no.of rows:"+row+"no.of columns"+col);
		for (int i = 1; i <=row ; i++) {
			test=report.createTest("verify Login Pgae");
			driver.manage().window().maximize();
			ImplicitlyWait(driver, 30);
			driver.get("https://admin-demo.nopcommerce.com/login");
			String userName=xl.getCellData("Login", i, 0);
			String password=xl.getCellData("Login", i, 1);
			String EmailId=xl.getCellData("Login", i, 2);
			String password1=xl.getCellData("Login", i,3);
			String firstName=xl.getCellData("Login", i, 4);
			String LastName=xl.getCellData("Login", i, 5);
			String Gender=xl.getCellData("Login", i, 6);

			System.out.println(i+"." +"userName:"+userName+" "+"Password:"+password+" "+"EmailId:"+EmailId+" "
					+"password1:"+password1+" "+"firstName:"+" "+firstName+"LastName:"+LastName+" "+"Gender:"+Gender);
			login.loginDetailsPage(userName, password);
			login.clickCustomersMenu();
			login.clickCustomerMenuItem();
			login.clickOnAddbutton();
			login.Enter_email(EmailId);
			login.Enter_password(password1);
			login.Enter_Frist_Name(firstName);
			login.Enter_Last_Name(LastName);
			login.select_gender(Gender);
			login.LogOut();
			Thread.sleep(3000);
			if (driver.getTitle().contains("Add a new customer / nopCommerce administration")) {
				Reporter.log("Login success",true);
				test.log(Status.PASS, "Login success");
				//write as login success in to results column
				xl.setCelldata("Login", i, 7, "Login Success", writeresults);
				//write as pass in to status column
				xl.setCelldata("Login", i, 8, "PASS", writeresults);
				xl.greencolour("Login", i, 8, writeresults);			
			}else{																																																		
				//get error message
				String message="Login Failed";
						//driver.findElement(By.xpath("//div[contains(text(),'Login was unsuccessful. Please correct the errors and try again.')]")).getText();
				Reporter.log(message,true);
				test.log(Status.FAIL,message );
				xl.setCelldata("Login", i, 7, message, writeresults);
				xl.setCelldata("Login", i, 8, "FAIL", writeresults);
				xl.redcolour("Login", i, 8, writeresults);
			}
			//report.createTest(test);
			report.flush();
		}
		xl.closefiles();
	}
}
