package Utlities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Framework.DataDrivenFramework;

public class ExtentReportPage extends DataDrivenFramework{

	public void ResultExtentReport() {
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/testout/myreport.html");
		htmlReporter.config().setDocumentTitle("Automation report");//Title of the report
		htmlReporter.config().setReportName("Functional Report");//Name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);
		report=new ExtentReports();
		report.attachReporter(htmlReporter);
		report.setSystemInfo("Hostname", "LocalHost");
		report.setSystemInfo("OS", "windows");
		report.setSystemInfo("TesterName", "Puneeth");
		report.setSystemInfo("BrowserName", "chrome");
	}
}
