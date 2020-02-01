@Sanity
Feature: Login function validation One 
 
Scenario: Successful Login with Valid Credentials 
	Given User able to open any browser 
	When User Enter URL 
	And User enters UserName and Password 
	And User able to click submit button 
	Then User able to validate Login status 
	And close browser 
		
