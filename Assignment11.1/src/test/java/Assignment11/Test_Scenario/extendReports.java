package Assignment11.Test_Scenario;



import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class extendReports {

	static ExtentReports report;

	@BeforeSuite
	public static ExtentReports getReport() {
		String reportpath = System.getProperty("user.dir") + "\\extendreports\\index.html";
		ExtentSparkReporter assginment = new ExtentSparkReporter(reportpath);
		assginment.config().setReportName("Assignment-11.1-ClearTrip");

		report = new ExtentReports();
		report.attachReporter(assginment);
		return report;
	}
}