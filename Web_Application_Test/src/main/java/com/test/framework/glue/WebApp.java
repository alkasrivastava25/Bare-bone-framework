package com.test.framework.glue;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.test.framework.Commons;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WebApp {

	public String driverPath = "C:/Web_Application_Test/driver/";
	public WebDriver driver;

	Commons com = new Commons();	


	
	public String screenshotpath = "C:/Web_Application_Test/screenshots";

	@Given("^Navigate to TravelexUK link$")
	public void navigate_to_TravelexUK_link() throws Throwable {

		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.travelex.co.uk/");

		driver.manage().window().maximize();

		com.screenShot(driver, screenshotpath,"Travelex home page");

	}

	@Given("^I resize the window$")
	public void i_resize_the_window_to_px() throws Throwable {


		Dimension dimension = new Dimension(550, 5156);
		driver.manage().window().setSize(dimension);

		com.screenShot(driver, screenshotpath,"Travelex resized screen");

	}

	@When("^I swipe left on the slider$")
	public void i_swipe_left_on_the_slider() throws Throwable {

		
		com.scroll_to(driver, com.getElementProperties("TravelexUK", "Travelexcontents"));
		com.screenShot(driver, screenshotpath,"Travelex money card screen");

		driver.findElement(By.xpath("//button[@type='button'][contains(text(),'2')]")).click();


		com.screenShot(driver, screenshotpath,"International money transfer screen");

		driver.findElement(By.xpath("//button[@type='button'][contains(text(),'3')]")).click();


		com.screenShot(driver, screenshotpath,"Buy foreign currency screen");

	}

	@Then("^I should see the third item displayed$")
	public void i_should_see_the_third_item_displayed() throws Throwable {


		driver.findElement(By.xpath("//button[@type='button'][contains(text(),'4')]")).click();

		com.screenShot(driver, screenshotpath,"Track rates screen");

	}

	@Then("^logout1$")
	public void logout1() throws Throwable {

		driver.quit();
		System.out.println("driver is closed");

	}
}
