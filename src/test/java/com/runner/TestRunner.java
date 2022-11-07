package com.runner;

import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/com/features/search.feature",
		glue = "com.stepdefs",
		plugin = {"html:target/cucumber.html"},
		monochrome = true
		)
public class TestRunner extends AbstractTestNGCucumberTests{

	public static Scenario scenario;
	@Override
	@DataProvider
	public Object[][] scenarios(){
		return super.scenarios();
	}

	
	
	
}