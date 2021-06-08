package com.swaglabs.swaglabs.POM.test;

import java.util.concurrent.TimeUnit;

import com.swaglabs.swaglabs.POM.pages.SwagLabsProductPage;
import com.swaglabs.swaglabs.utils.DriverUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSwagLabsProductPage {

    WebDriver driver;
    SwagLabsProductPage labsProductPage;

    @BeforeTest(alwaysRun = true)
    public void setup() {
        DriverUtils.setDriver();
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
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

    @Test
    public void test_add_item_to_cart() {
        labsProductPage = new SwagLabsProductPage(driver);
        labsProductPage.addToCart();
        Assert.assertTrue(labsProductPage.isAddedtoCart());
    }

    @Test
    public void test_remove_from_cart() {
        labsProductPage = new SwagLabsProductPage(driver);
        labsProductPage.removeFromCart();
        Assert.assertTrue(labsProductPage.isNotOnCart());
    }
}
