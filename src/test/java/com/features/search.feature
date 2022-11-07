Feature: WA Cart application test

Scenario: Search Functionality

Given user on home page
When  user search existing brand name
Then user should be visible related products

Given user on search result page
When user search from search criteria field
Then user should be displayed search products
