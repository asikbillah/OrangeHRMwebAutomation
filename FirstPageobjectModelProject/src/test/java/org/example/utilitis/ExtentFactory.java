package org.example.utilitis;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentFactory {
    public static final ExtentReports extentReports = new ExtentReports();
    public synchronized static ExtentReports getInstance(){
        ExtentSparkReporter reporter = new ExtentSparkReporter("./reports/TestReport.html");
        reporter.config().setReportName("Orange HRM");
        extentReports.attachReporter(reporter);
        return extentReports;
    }
}
