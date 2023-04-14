package com.ecommerce.ebay.pages;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecommerce.ebay.utilities.CustomDriver;
import com.ecommerce.ebay.utilities.DynamicXpathUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.support.ui.Select;


public class Page_ebay {

	private CustomDriver driver;
	private static final Logger log = LogManager.getLogger(Page_ebay.class.getName());	

	public Page_ebay(WebDriver driver) {
		this.driver = new CustomDriver(driver);
	}

	private String eBayLogo ="gh-la";
	private String selectBycaterogyButton = "gh-cat-box";	
	private String shopBycaterogyButton = "gh-shop-a";
	private String subMenuItemfromShopCategory = "//*[@id='gh-sbc']//li/a[contains(text(),'%replaceable%')]";
	private String selectCategorydropdown ="//*[@id='gh-cat-box']/select[@id='gh-cat']";
	private String breadcrumbText = "//ul/li[3]/a[@class='seo-breadcrumb-text']";
	private String categoryFromLeftNavigation = "//li/a[contains(text(),'%replaceable%')]";
	private String breadcrumbCellPhoneSmartPhones = "//ul/li[4]/a[@class='seo-breadcrumb-text']";
	private String seeAllButton = "//span[contains(text(),'Shop by Brand')]/preceding-sibling::span";
	private String dialog_window = "//div[@class='dialog__window']";
	private String screen_size_tab = "//*[@role='tab' and @data-aspecttitle='aspect-Screen%20Size']";
	private String location_tab = "//*[@role='tab' and @data-aspecttitle='location']";
	private String price_tab ="//*[@role='tab' and @data-aspecttitle='price']";
	private String screen_size_chk_box = "c3-subPanel-Screen%20Size_4.0%20-%204.4%20in";
	private String input_price_from ="//input[@aria-label='Minimum Value, US Dollar']";
	private String input_price_to ="//input[@aria-label='Maximum Value, US Dollar']";
	private String worldwide_chk_box ="//input[@value='Worldwide']";
	private String apply_button = "//button[@aria-label='Apply']";
	private String filter_applied_button = "//button/span[contains(text(),'filters applied')]";
	private String filter_applied_size ="//li[@class='brm__aspect-item brm__aspect-item--applied']/a/span[contains(text(), '%replaceable%')]";
	private String filter_applied_price ="//li[@class='brm__aspect-item brm__aspect-item--applied']/a/span[contains(text(), '%replaceable%')]";	
	private String filter_applied_location ="//li[@class='brm__aspect-item brm__aspect-item--applied']/a/span[contains(text(), '%replaceable%')]";
	private String searchTextbox = "gh-ac";
	private String searchButton ="gh-btn";
	private String allListslink = "//span[@aria-label='All Listings Current view']";
	private String searchfirstresults ="//span[@role='heading']/span[@class='BOLD' and contains(text(),'%replaceable%')]";

	//Navigate to Website
	public void navigateToWebSite(String url) throws Exception
	{
		try {
			//Launch URL
			driver.getDriver().get(url);
			String originalTitle = driver.getDriver().getTitle().strip();
			String expectedTitle = "Electronics, Cars, Fashion, Collectibles & More | eBay";			
			Assert.assertEquals(originalTitle,expectedTitle,"Titles of the website do not match");			
		} catch (Exception | AssertionError e) {
			throw new Exception(e.toString());
		}

	}

	//Select Item from Shop by Category
	public void clickOnMenuItemfromShopByCategory(String category) throws Exception
	{
		try {
			driver.findElement(By.id(shopBycaterogyButton)).click();
			String [] subMenuText = category.split(">");		
			String newxpath = DynamicXpathUtils.getXpath(subMenuItemfromShopCategory, subMenuText[2].strip());
			driver.findElement(By.xpath(newxpath)).click();	

			WebElement breadcrumElement = driver.findElement(By.xpath(breadcrumbText));
			Assert.assertTrue(breadcrumElement.isDisplayed(),"subMenu not found in shop by category");			
		} catch (Exception | AssertionError e) {
			throw new Exception(e.toString());
		}
	}

	//Select Category in left hand side navigation
	public void selectCategoryfromLeftNavigation(String leftNavigationCategory) throws Exception
	{
		try {
			String newxpath = DynamicXpathUtils.getXpath(categoryFromLeftNavigation, leftNavigationCategory.strip());
			driver.findElement(By.xpath(newxpath)).click();				
			WebElement breadcrumbSmartphonesElement = driver.findElement(By.xpath(breadcrumbCellPhoneSmartPhones));		
			Assert.assertEquals(breadcrumbSmartphonesElement.getText().strip(), leftNavigationCategory.strip(), "Category not selected");			
		} catch (Exception | AssertionError e) {
			throw new Exception(e.toString());
		}			
	}	

	//Click See All button appears under Shop by brand or Shop by Network
	public void clickSeeAllbutton(String seeAllBtn)
	{
		driver.findElement(By.xpath(seeAllButton)).click();
		//Check for dialog window
		WebElement dlg_wdw = driver.findElement(By.xpath(dialog_window));
		if(dlg_wdw.isDisplayed())
		{
			log.info("dialog window opened successfully");
		}
		else
		{
			log.error("dialog window failed to load");
		}
	}	

	//Select filters	
	public void SelectfiltersInPopup(String screenSize,String priceFrom,String priceTo,String location)
	{		
		//Select Screen Size tab
		driver.findElement(By.xpath(screen_size_tab)).click();		

		//Select screen size checkbox values
		driver.findElement(By.id(screen_size_chk_box)).click();

		//Select Price tab and enter values
		driver.findElement(By.xpath(price_tab)).click();
		driver.findElement(By.xpath(input_price_from)).sendKeys(priceFrom.strip());
		driver.findElement(By.xpath(input_price_to)).sendKeys(priceTo.strip());

		//Select location with value worldwide
		driver.findElement(By.xpath(location_tab)).click();
		driver.findElement(By.xpath(worldwide_chk_box)).click();

		//Click Apply button
		driver.findElement(By.xpath(apply_button)).click();
	}	

	public void VerifyfiltersApplied(String inscreenSize, String inPrice, String inItemLocation)
	{
		//Click on filter applied button
		driver.findElement(By.xpath(filter_applied_button)).click();

		String size = DynamicXpathUtils.getXpath(filter_applied_size, inscreenSize.strip());
		String pricetext = DynamicXpathUtils.getXpath(filter_applied_price, inPrice.strip());
		String location = DynamicXpathUtils.getXpath(filter_applied_location, inItemLocation.strip());

		if(driver.findElement(By.xpath(size)).getText().contains(inscreenSize) && driver.findElement(By.xpath(pricetext)).getText().contains(inPrice) && driver.findElement(By.xpath(location)).getText().contains(inItemLocation))
		{
			log.info("filters are selected successfully");
		}
		else
		{
			log.error("Error in while selecting filters");
		}

	}	

	//Enter value in Search box
	public void InputText(String valuetoEnter) throws Exception
	{
		try {
			driver.findElement(By.id(searchTextbox)).clear();
			driver.findElement(By.id(searchTextbox)).sendKeys(valuetoEnter);		
			String actualtext = driver.findElement(By.id(searchTextbox)).getAttribute("value");
			Assert.assertEquals(actualtext.strip(), valuetoEnter.strip(), "Text not entered in search field");				
		} catch (Exception | AssertionError e) {
			throw new Exception(e.toString());
		}

	}	

	//Change Search Category
	public void changeSearchcategory(String category)
	{
		driver.findElement(By.id(selectBycaterogyButton)).click();	
		Select sel = new Select(driver.findElement(By.xpath(selectCategorydropdown)));		
		sel.selectByVisibleText(category);		
		//Click on search button
		driver.findElement(By.id(searchButton)).click();		
	}	

	public void verifyPageloads()
	{	
		//After page loads check for All listings link
		if(driver.findElement(By.xpath(allListslink)).isDisplayed())
		{
			log.info("Page loaded successfully with search results");
		}
		else
		{
			log.error("Page not loaded with search results");
		}
	}		

	//Verify search results
	public void verifySearchresults(String searchtext)
	{			
		String newxpath = DynamicXpathUtils.getXpath(searchfirstresults, searchtext.strip());		
		if(driver.findElement(By.xpath(newxpath)).isDisplayed())
		{
			log.info("Search results found for the search input  "+searchtext);
		}
		else
		{
			log.error("No search resutls for "+searchtext);
		}
	}	
}




