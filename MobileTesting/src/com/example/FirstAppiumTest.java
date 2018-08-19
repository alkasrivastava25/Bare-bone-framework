package com.example;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class FirstAppiumTest {
	
		 
		  public static final String USERNAME = "alka24";
		  public static final String ACCESS_KEY = "073029b4-3059-4445-9ee4-0a785ab16109";
		  public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
		 
		  public static void main(String[] args) throws Exception {
		 
		    DesiredCapabilities capabilities = new DesiredCapabilities();
		    capabilities.setCapability("platformName", "Android");
		    capabilities.setCapability("deviceName", "Samsung Galaxy S4 Emulator");
		    capabilities.setCapability("platformVersion", "4.4");
		    capabilities.setCapability("app", "http://saucelabs.com/example_files/DealsPure.apk");
		    capabilities.setCapability("browserName", "");
		    capabilities.setCapability("deviceOrientation", "portrait");
		    capabilities.setCapability("appiumVersion", "1.5.3");
		 
		    WebDriver driver = new AndroidDriver<>(new URL(URL), capabilities);
		 
		    /**
		     * Test Actions here...
		     */
		 
		    driver.quit();
		  }
		
	

}
