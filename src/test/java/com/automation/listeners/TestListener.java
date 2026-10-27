
package com.automation.listeners;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.base.BaseTest;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object testObject = result.getInstance();

        if (testObject instanceof BaseTest) {

            BaseTest baseTest = (BaseTest) testObject;

            String timeStamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
            );

            String testName = result.getMethod().getMethodName();

            String fileName = "FAILED_" + testName + "_" + timeStamp;

            try {
                if (baseTest.getDriver() != null) {
                    baseTest.takeScreenshot(fileName);

                    System.out.println(
                        "Failure screenshot captured for: " + testName
                    );
                }
            } catch (Exception e) {
                System.out.println(
                    "Could not capture failure screenshot: "
                    + e.getMessage()
                );
            }
        }
    }
}
