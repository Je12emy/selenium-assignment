package com.swaglabs.swaglabs.POM.test;

import java.util.concurrent.TimeUnit;

import com.swaglabs.swaglabs.Utils;
import com.swaglabs.swaglabs.POM.pages.SwagLabsProductPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSwagLabsProductPage {

    WebDriver driver;
    SwagLabsProductPage labsProductPage;

    @BeforeTest(alwaysRun = true)
    public void setup() {
        Utils.setDriver();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void test_content_is_displayed() {
        labsProductPage = new SwagLabsProductPage(driver);
        Assert.assertTrue(labsProductPage.elementsAreDisplayed());
    }

    @Test
    public void test_content_is_not_displayed() {
        labsProductPage = new SwagLabsProductPage(driver, false);
        Assert.assertFalse(labsProductPage.elementsAreDisplayed());
    }
}
