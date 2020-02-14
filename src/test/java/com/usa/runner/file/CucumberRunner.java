package com.usa.runner.file;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = { ".//Fetatures/" },
		glue = "com.usa.login.functions", 
		dryRun = false, 
		monochrome = true, 
				strict = false,
						plugin= {"pretty","html:target/cucumber-reports/cucumber.json", "json:target/cucumber.json" }, 
		tags = { "@sanity" })
public class CucumberRunner extends AbstractTestNGCucumberTests {

}
