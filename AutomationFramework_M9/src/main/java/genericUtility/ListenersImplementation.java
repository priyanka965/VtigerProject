package genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenersImplementation implements ITestListener{
    public ExtentTest test;
	public ExtentReports report;
	public void onTestStart(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"----Started");
		
		 test =report.createTest(methodname);
		
	}


	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"----Passed");
		test.log(Status.PASS, methodname+"----Passed");
		
	}

	
	public void onTestFailure(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"----Failed");
		test.log(Status.FAIL, methodname+"----Failed");
		test.log(Status.INFO, result.getThrowable());
	
		
		WebDriverUtility wutil=new WebDriverUtility();
		JavaUtility jutil =new JavaUtility();
		
		String screenshotname = methodname+"- "+jutil.toGetSystemDateAndTime();
		
		try {
			String path = wutil.toTakeScreenshot(BaseClass.sDriver, screenshotname);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		

	}


	public void onTestSkipped(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		System.out.println(methodname+"----Skipped");
		test.log(Status.SKIP, methodname+"----Skipped");
	}

	
	public void onStart(ITestContext context) {
		System.out.println("----Suite Execution Started---");
		
		ExtentSparkReporter htmlreport=new ExtentSparkReporter("./ExtentReports/Reports-"+new JavaUtility().toGetSystemDateAndTime()+".html");
		htmlreport.config().setDocumentTitle("VTIGER EXECUTION REPORT");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setReportName("Vtiger Execution Report");
		
		report=new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("URL", "http:localhost:8888/");
		report.setSystemInfo("username", "admin");
		report.setSystemInfo("password", "password");
		report.setSystemInfo("Base Browser", "chrome");
		report.setSystemInfo("Reporter Name", "Priyanka");
		
	}

	
	public void onFinish(ITestContext context) {
		System.out.println("----Suite Execution Finished---");
		report.flush();
	}
	

}
