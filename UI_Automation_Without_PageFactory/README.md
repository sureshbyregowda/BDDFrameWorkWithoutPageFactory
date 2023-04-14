# BDDFrameWorkWithoutPageFactory
# selenium_BDD_framework

Behavioural driven development automation framework using selenium, cucumber-java, testng and maven.

**Tools and technologies used:**

1. Language: Java 11
2. Testing framework: Testng
3. BDD framework: Cucumber jvm
4. Automation tool: Selenium webdriver
5. Build tool: maven
6. Logging: log4j
7. Reports: extentreports ( Plugin used - com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter in TestRunner file)


**Setting up the project:**
$ cd UI_Automation_Without_PageFactory
$ mvn clean install

**Running the tests:** There are 2 scenarios named as @smoke.
$ mvn test

**To run specific scenario:**
$ mvn test -Dcucumber.options="--tags @smoke"


**Folder Structure:**

src/test/java 
			com.ecommerce.ebay.cucumberspecifics
									 TestRunner.java ---  TestRunner File is used to build a Communication between Feature Files and StepDefinition Files.

			com.ecommerce.ebay.hooks
									hooks.java - used for setup and teardown of the environment before and after each scenario
			
			com.ecommerce.ebay.pages
									- It has web elements corresponds to the specific pages and also contains Page methods which perform operations on those WebElements.

			com.ecommerce.ebay.stepdefs
								- A step definition file in Cucumber holds the test method and code which are mapped to the Gherkin test case steps on the feature file.

			com.ecommerce.ebay.utilities	
									- Contains utilities related to driver,Customdriver and dynamicXpathUtilities.

src/test/resources
				-- contains extent.properties for reporting

src/main/resources
				-- contains log4j2.xml file with XML configuration provides a simple way of setting up logging in your Java application.

Features 
		-- contains feature files which store high-level description of scenarios and steps in the Gherkin language

logs
	-- contains .log files which are generated during each run.


Reports
	-- Creates Extent html report and PDF Reports in Reports folder for each test run
  
 pom.xml - It is an XML file that contains information about the project and configuration details used by Maven to build the project
           Dependencies related to selenium-java, cucumber-java, cucumber-testng, cucumber-jvm-deps, extentreports-cucumber7-adapter, gherkin, 
           exentreports, log4j-api, webdrivermanager, poi etc..
           Build plugins -  maven-compiler-plugin, maven-surefire-plugin

			
**Features of the framework:**
1. BDD framework using Cucumber-jvm. Features can be written easily using Given,When,And Then etc.
2. Screenshot would be taken if any scenario failed and saved under  /target/cucumber-reports/screenshots/  folder.
3. Extent html report and PDF Reports gets generated after each test run and can be found /Reports folder.
