package com.ecommerce.ebay.stepdefs;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.ecommerce.ebay.hooks.*;
import com.ecommerce.ebay.pages.*;
import com.ecommerce.ebay.utilities.*;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps_ebay {
	private static CustomDriver driver;
	private static final Logger log = LogManager.getLogger(Steps_ebay.class.getName());
	private Page_ebay page_ebay;

	public Steps_ebay(DriverHelper driverHelper)
	{
		page_ebay = new Page_ebay(driverHelper.driver);
	}	

	@Given("I navigate to the Website {string}")
	public void i_navigate_to_the_website(String url) throws Throwable {
		page_ebay.navigateToWebSite(url);	
	}

	@When("I navigate to Search by {string}")
	public void i_navigate_to_search_by(String category) throws Throwable {				
		page_ebay.clickOnMenuItemfromShopByCategory(category);		
	}

	@When("I click {string} in the left hand side navigation section")
	public void i_click_in_the_left_hand_side_navigation_section(String leftNavigationCategory) throws Throwable {
			page_ebay.selectCategoryfromLeftNavigation(leftNavigationCategory);	
	}

	@When("I click {string} button appears under Shop by brand or Shop by Network")
	public void i_click_button_appears_under_shop_by_brand_or_shop_by_network(String seeAllBtn) {
		page_ebay.clickSeeAllbutton(seeAllBtn);
	}

	@When("I Add three filters apperaing in the pop up and click apply button")
	public void i_add_three_filters_apperaing_in_the_pop_up_and_click_apply_button(DataTable filterdetails) throws Throwable {
		List<List<String>> fildetails = filterdetails.asLists(String.class);
		//Read all filter value details from the Data Table
		String screenSize = fildetails.get(1).get(0);   //screenSize	
		String priceFrom = fildetails.get(1).get(1);  	//price from
		String priceTo = fildetails.get(1).get(2);  	//price from		
		String location = fildetails.get(1).get(3);   	//location
		page_ebay.SelectfiltersInPopup(screenSize,priceFrom,priceTo,location);
	}	


	@Then("Verify that the filter tags {string},{string},{string} are applied")
	public void verify_that_the_filter_tags_are_applied(String screenSize, String Price, String ItemLocation) throws Throwable{
		page_ebay.VerifyfiltersApplied(screenSize, Price, ItemLocation);
	}  

	@When("I type {string} in the search bar\"")
	public void i_type_in_the_search_bar(String searchvalue) throws Throwable {
		page_ebay.InputText(searchvalue);
	}

	@When("I Change the search category {string} and click search button")
	public void i_change_the_search_category_and_click_search_button(String category) throws Throwable {
		page_ebay.changeSearchcategory(category);
	}

	@Then("Verify that the page loads completely")
	public void verify_that_the_page_loads_completely() throws Throwable{
		page_ebay.verifyPageloads();
	}

	@Then("Verify that the first result name matches with the search string {string}")
	public void verify_that_the_first_result_name_matches_with_the_search_string(String searchtext) throws Throwable {
		page_ebay.verifySearchresults(searchtext);
	}	

}
