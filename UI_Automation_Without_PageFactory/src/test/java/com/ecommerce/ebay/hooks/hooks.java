package com.ecommerce.ebay.hooks;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.ecommerce.ebay.utilities.DriverHelper;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.javadoc.doclet.Reporter;

public class hooks {

	public DriverHelper driverHelper;

	public hooks(DriverHelper driverHelper) {
		this.driverHelper=driverHelper;
	}

	//Before scenario
	@Before(order=1)
	public void beforescenario()
	{
		System.out.println("Before scenario - Test Starts");		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized"); 
		driverHelper.driver=new ChromeDriver(options);
		driverHelper.driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);	
	}
	
	//After scenario
	@After
	public void afterscenario(Scenario scenario)
	{
		
		if(scenario.isFailed())
		{
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try
			{
				File sourcePath = ((TakesScreenshot) driverHelper.driver).getScreenshotAs(OutputType.FILE);
				File destinationPath = new File(System.getProperty("user.dir")+"/target/cucumber-reports/screenshots/" + screenshotName + ".png");
				FileHandler.copy(sourcePath, destinationPath);				
				ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(destinationPath.toString());
			}
			catch ( Exception e)
			{
				System.out.println(e.toString());
			}
		}
				
		driverHelper.driver.quit();
		System.out.println("After scenario - Test Ends");
	}

}
