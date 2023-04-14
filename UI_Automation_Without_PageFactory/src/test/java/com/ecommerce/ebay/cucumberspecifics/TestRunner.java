package com.ecommerce.ebay.cucumberspecifics;

import java.io.File;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;


@CucumberOptions(
        features = "./Features",
        glue = {"com.ecommerce.ebay.hooks","com.ecommerce.ebay.stepdefs"},
        tags = "@Smoke",
        plugin = {
                "pretty",
                "html:Reports/HtmlReports/cucumber-pretty",
                "json:Reports/JsonReports/cucumber-reports/AutomationTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt",
                "html:target/cucumber-reports/report.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"            
        },
        monochrome=true
        )
public class TestRunner extends AbstractTestNGCucumberTests{

}
