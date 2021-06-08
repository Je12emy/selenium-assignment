package com.swaglabs.swaglabs.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class DriverUtils {
    public static void setDriver() {
        String driverPath = "/home/dio/Documents/Github/Je12emy/selenium-assignment/geckodriver";
        System.setProperty("webdriver.gecko.driver", driverPath);
    }

    public static void takeScreenshot(WebDriver driver, String fileName) throws IOException {
        TakesScreenshot ss = ((TakesScreenshot) driver);
        File src = ss.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "/src/main/resources/" + fileName);
        FileUtils.copyFile(src, dest);
        Reporter.log("<img src=" + dest + "/>");
    }
}
