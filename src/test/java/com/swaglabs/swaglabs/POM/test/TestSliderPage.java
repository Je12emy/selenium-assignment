package com.swaglabs.swaglabs.POM.test;

import com.swaglabs.swaglabs.POM.pages.SliderPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSliderPage {
    WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        driver = new FirefoxDriver();
    }

    @AfterTest()
    public void teardown() {
        driver.quit();
    }

    // @Test()
    // public void test_should_update_the_slider_value_drag_and_drop() {
    //     SliderPage sliderPage = new SliderPage(driver);
    //     sliderPage.setStepsDragDrop(1);
    //     sliderPage.assertSize(2.5f);
    // }

    @Test()
    public void test_should_update_the_slider_value_keys() {
        SliderPage sliderPage = new SliderPage(driver);
        sliderPage.setSteps(2);
        sliderPage.assertSize(1);
    }
}
