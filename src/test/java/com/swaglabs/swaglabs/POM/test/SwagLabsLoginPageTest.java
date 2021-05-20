package com.swaglabs.swaglabs.POM.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import com.swaglabs.swaglabs.Utils;
import com.swaglabs.swaglabs.POM.pages.SwagLabsLogin;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SwagLabsLoginPageTest {

	WebDriver driver;
	SwagLabsLogin labsLogin;
	final String url = "https://www.saucedemo.com/";

	@BeforeTest
	void setup() {
		// Utils.setDriver();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(url);
	}

	@AfterMethod
	void clear() {
		labsLogin.clearForm();
		driver.get(url);
	}

	@Test
	void test_login_page_displayed() {
		labsLogin = new SwagLabsLogin(driver);
		Assert.assertTrue(labsLogin.isLogoDisplayed());
	}

	@Test
	void test_missing_credentials_error() {
		labsLogin = new SwagLabsLogin(driver);
		labsLogin.loginWithNoCredentials();
		Assert.assertTrue(labsLogin.isMissingUsernameError());
	}

	@Test
	void test_missing_username_error() {
		labsLogin = new SwagLabsLogin(driver);
		labsLogin.loginWithOnlyPassword();
		Assert.assertTrue(labsLogin.isMissingUsernameError());
	}

	@Test
	void test_missing_password_error() {
		labsLogin = new SwagLabsLogin(driver);
		labsLogin.loginWithOnlyUserName();
		Assert.assertTrue(labsLogin.isMissingPasswordError());
	}

}
