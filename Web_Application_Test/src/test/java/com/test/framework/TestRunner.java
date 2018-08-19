package com.test.framework;

import java.io.File;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/"
		,glue={"com.test.framework.glue"}
		 ,plugin = {"com.cucumber.listener.ExtentCucumberFormatter:"}
		,tags={"@API"}
		
)
public class TestRunner {
	
	@BeforeClass
	public static void setup() {
	    ExtentProperties extentProperties = ExtentProperties.INSTANCE;
	    extentProperties.setReportPath("output/myreport.html");
	}
	
	@AfterClass
    public static void teardown() {
        Reporter.loadXMLConfig(new File("src/main/java/com/test/config/extent-config.xml"));
        Reporter.setSystemInfo("user", System.getProperty("user.name"));
        Reporter.setSystemInfo("os", "Mac OSX");
        Reporter.setTestRunnerOutput("Sample test runner output message");
    }

}
