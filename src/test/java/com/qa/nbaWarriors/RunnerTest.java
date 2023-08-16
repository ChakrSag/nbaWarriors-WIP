package com.qa.nbaWarriors;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/main/java/com/qa/nbaWarriors/features/" }, glue = {
        "com.qa.StepDefinitions" }, monochrome = true,
        plugin = { "pretty", "html:target/cucumber", "json:target/cucumber.json",
                "com.cucumber.listener.ExtentCucumberFormatter:output/report.html", "com.qa.Reporting.CustomFormatter" })
public class RunnerTest {
}
