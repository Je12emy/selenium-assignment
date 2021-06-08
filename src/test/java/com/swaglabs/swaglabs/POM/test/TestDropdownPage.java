package com.swaglabs.swaglabs.POM.test;

import java.io.IOException;

import com.swaglabs.swaglabs.POM.pages.DropdownPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestDropdownPage {

    WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        driver = new FirefoxDriver();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test()
    public void test_should_select_item_from_dropdown() throws IOException {
        DropdownPage dropdownPage = new DropdownPage(driver);
        dropdownPage.selectValue(2);
        dropdownPage.assertSelectedValue("Option 2");
    }

}
