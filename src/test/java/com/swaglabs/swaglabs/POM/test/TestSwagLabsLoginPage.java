package com.swaglabs.swaglabs.POM.test;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import com.swaglabs.swaglabs.Utils;
import com.swaglabs.swaglabs.POM.pages.SwagLabsLogin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

public class TestSwagLabsLoginPage {

	WebDriver driver;
	SwagLabsLogin labsLogin;

	@BeforeTest(alwaysRun = true)
	public void setup() {
		Utils.setDriver();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterMethod(dependsOnGroups = { "Login Errors" }, onlyForGroups = { "Login Errors" })
	public void clear() {
		labsLogin.clearForm();
	}

	@Test(description = "Fill the form with empty credentials", groups = { "Login Errors" })
	public void test_missing_credentials_error() {
		labsLogin = new SwagLabsLogin(driver);
		labsLogin.loginWithNoCredentials();
		Assert.assertTrue(labsLogin.isMissingUsernameError());
	}

	@Test(description = "Fills the form but with a empty username field", groups = { "Login Errors" })
	public void test_missing_username_error() {
		labsLogin = new SwagLabsLogin(driver);
		labsLogin.loginWithOnlyPassword();
		Assert.assertTrue(labsLogin.isMissingUsernameError());
	}

	@Test(description = "Fills the form but with a empty password field", groups = { "Login Errors" })
	public void test_missing_password_error() {
		labsLogin = new SwagLabsLogin(driver);
		labsLogin.loginWithOnlyUserName();
		Assert.assertTrue(labsLogin.isMissingPasswordError());
	}

	@Test(description = "Checks if the logo is displayed.", groups = { "Login Test" })
	public void test_login_page_displayed() {
		labsLogin = new SwagLabsLogin(driver);
		Assert.assertTrue(labsLogin.isLogoDisplayed());
	}

	@Test(description = "Fills the form with valid credentials and signs in", groups = { "Login Test" })
	public void test_signin() {
		labsLogin = new SwagLabsLogin(driver);
		labsLogin.loginWithStandardUser();
		Assert.assertTrue(labsLogin.isSignedIn());
	}

}
