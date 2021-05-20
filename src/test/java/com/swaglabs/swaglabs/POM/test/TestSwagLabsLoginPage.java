package com.swaglabs.swaglabs.POM.test;

import java.util.concurrent.TimeUnit;

import com.swaglabs.swaglabs.Utils;
import com.swaglabs.swaglabs.POM.pages.SwagLabsLogin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSwagLabsLoginPage {

	WebDriver driver;
	SwagLabsLogin labsLogin;
	final String url = "https://www.saucedemo.com/";

	@BeforeTest
	public void setup() {
		Utils.setDriver();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(url);
	}

	@AfterMethod
	public void clear() {
		labsLogin.clearForm();
		driver.get(url);
	}

	@Test(description = "Checks if the logo is displayed.")
	public void test_login_page_displayed() {
		labsLogin = new SwagLabsLogin(driver);
		Assert.assertTrue(labsLogin.isLogoDisplayed());
	}

	@Test(description = "Fill the form with empty credentials")
	public void test_missing_credentials_error() {
		labsLogin = new SwagLabsLogin(driver);
		labsLogin.loginWithNoCredentials();
		Assert.assertTrue(labsLogin.isMissingUsernameError());
	}

	@Test(description = "Fills the form but with a empty username field")
	public void test_missing_username_error() {
		labsLogin = new SwagLabsLogin(driver);
		labsLogin.loginWithOnlyPassword();
		Assert.assertTrue(labsLogin.isMissingUsernameError());
	}

	@Test(description = "Fills the form but with a empty password field")
	public void test_missing_password_error() {
		labsLogin = new SwagLabsLogin(driver);
		labsLogin.loginWithOnlyUserName();
		Assert.assertTrue(labsLogin.isMissingPasswordError());
	}

}
