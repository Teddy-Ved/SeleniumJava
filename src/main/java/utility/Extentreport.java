package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Extentreport {
	
	public static ExtentReports createExtentReport()
	{
		String reportpath = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter spark = new ExtentSparkReporter(reportpath);
		spark.config().setReportName("Automation Dashboard");
		spark.config().setDocumentTitle("Daily Automation Report");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(spark);
		
		return extent;
		
	}
	
	

}
