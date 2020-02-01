@Regression 
Feature: Login function validation One 


Scenario Outline: Successful Login with Valid Credentials scenario Outline
	Given User able to open chrome browser 
	And  Put "<URL>" and go to login page 
	When User able to click my account 
	And User able to use valid username"<userName>" 
	And User able to put valid  password"<password>" 
	And User able to click singin button 
	Then User able to validated Login status 
	And closeing the browser 
	Examples: 
		|URL							|userName				|password |
		|http://www.gcrit.com/build3/	|sarowerny@gmail.com	|student  |
		|http://www.gcrit.com/build3/	|smarttech@gmail.com	|smarttech|
		|http://www.gcrit.com/build3/	|smarttech@gmail.com	|smarttech|