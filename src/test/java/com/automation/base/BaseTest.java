
package com.automation.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void takeScreenshot(String fileName) {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Path folder = Paths.get("screenshots");

		try {
			Files.createDirectories(folder);

			Path destination = folder.resolve(fileName + ".png");

			Files.copy(source.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

			System.out.println("Screenshot saved: " + destination.toAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
