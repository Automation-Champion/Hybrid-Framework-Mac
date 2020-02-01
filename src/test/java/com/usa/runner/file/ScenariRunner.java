package com.usa.runner.file;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(plugin = { "pretty", "html:target/cucumber-reports/cucumber.json", "html:target/cucumber-reports/report.html",
		"json:target/cucumber.json" }, features = { ".//Fetatures/" }, glue = {
				"com.usa.stepdefinition" }, dryRun = false, monochrome = true, strict = false, tags = { "@sanity" })
public class ScenariRunner extends AbstractTestNGCucumberTests {

	
	
}
