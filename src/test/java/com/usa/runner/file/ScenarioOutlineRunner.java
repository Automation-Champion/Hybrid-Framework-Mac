package com.usa.runner.file;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

	@CucumberOptions(
			features = { ".//Fetatures/" },
			glue = { "com.usa.stepdefinition" },
			dryRun = false, 
			monochrome = true, 
					strict = false,
							plugin= {"pretty","html:target/cucumber-reports/cucumber.json", "json:target/cucumber.json" },
			tags = { "@Regression" })
	public class ScenarioOutlineRunner extends AbstractTestNGCucumberTests {

	}

