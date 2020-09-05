package Framework;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import CustomerLoginPage.ElementException;
import CustomerLoginPage.LoginDetails;
import ExcelSheet.SheetDetails;
import Utlities.ExcelHomePage;
import Utlities.ExcelLoginPage;
import Utlities.ExtentReportPage;

public class DataDrivenFramework extends ElementException {
	//MSKUMAR     Abcd@123456
	//visni 
	public static WebDriver driver;
	public static SheetDetails xl;
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports report;
	public static ExtentTest test;
	public static String writeresults=System.getProperty("user.dir")+"\\src\\main\\java\\resourseExcelSheet\\ResultsStatus.xlsx";
	@BeforeClass
	public void startUp() {
		ExtentReportPage re=new ExtentReportPage();
		re.ResultExtentReport();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("headless");
		System.setProperty("webdriver.chrome.driver", "E:\\E\\floder E\\Browsers\\New folder (2)\\New folder\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	@Test(priority=1)
	public void VerifyLoginPage() throws Throwable {
		ExcelLoginPage elp=new ExcelLoginPage();
		elp.CustomerLoginPageDetails();
	}
	@AfterTest
	public void EndReport() {
		report.flush();
	}
	@AfterClass
	public void tearDown() throws Exception {
		//LoginDetails ld=new LoginDetails();
		//ld.LogOut();
		closeBrowser(driver);
	}
}
