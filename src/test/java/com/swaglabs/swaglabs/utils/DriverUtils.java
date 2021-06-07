package com.swaglabs.swaglabs.utils;

public class DriverUtils {
    public static void setDriver() {
        String driverPath = "/home/dio/Documents/Github/Je12emy/selenium-assignment/geckodriver";
        System.setProperty("webdriver.gecko.driver", driverPath);
    }
}
