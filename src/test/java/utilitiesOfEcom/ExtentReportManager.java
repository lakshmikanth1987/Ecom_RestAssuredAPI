package utilitiesOfEcom;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";
		
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
		sparkReporter.config().setDocumentTitle("RestAssuredAutomation Report");
		sparkReporter.config().setReportName("ECom");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Application", "Pet Store User API");
		extent.setSystemInfo("User","Lakshmikanth");
	}

	 public void onTestSuccess(ITestResult result) {
		 test=extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.createNode(result.getName());
		 test.log(Status.PASS,"Test Passed");
	 }
	 
	 public void onTestFailure(ITestResult result) {
		 test=extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.createNode(result.getName());
		 test.log(Status.FAIL,"Test Failed");
		 test.log(Status.FAIL, result.getThrowable().getMessage());
	 }
	 
	 public void onTestSkip(ITestResult result) {
		 test=extent.createTest(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.createNode(result.getName());
		 test.log(Status.SKIP,"Test SKIPPED");
		 test.log(Status.SKIP, result.getThrowable().getMessage());
	 }
	 
	 public void onFinish(ITestContext testContext) {
		 extent.flush();
	 }
}
