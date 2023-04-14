 Feature: Search functionality
  As a customer
  can access a product via category by applying multiple filters and also via search.
  
  Background:
  	Given I navigate to the Website "https://www.ebay.com"

  @Smoke
  Scenario: Access a Product via category after applying multiple filters
    When I navigate to Search by "category > Electronics > Cell phones & accessories"
    And  I click "Cell Phones & Smartphones" in the left hand side navigation section
    And  I click "See All" button appears under Shop by brand or Shop by Network
    And  I Add three filters apperaing in the pop up and click apply button
    		 | screen size  | Price from | Price To | location |
    		 | 4.0 - 4.4 in |			1      | 10       | Worldwide|
    Then Verify that the filter tags "Screen Size","Price","Item Location" are applied

  @Smoke
  Scenario: Access a Product via Search
    When I type "MacBook" in the search bar"
    And  I Change the search category "Computers/Tablets & Networking" and click search button
    Then Verify that the page loads completely 
    And  Verify that the first result name matches with the search string "MacBook"   