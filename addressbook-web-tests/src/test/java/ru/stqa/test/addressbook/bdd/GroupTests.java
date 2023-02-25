package ru.stqa.test.addressbook.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions (feature = "classpath:bdd", plugin = {"pretty", "html:build/cucumber-report"})

public class GroupTests extends AbstractTestNGCucumberTests {
}
