package com.swaglabs.swaglabs;

public class Utils {
    public static void setDriver() {
        String driverPath = "/home/dio/Documents/Github/Je12emy/swaglabs/geckodriver";
        System.setProperty("webdriver.gecko.driver", driverPath);
    }
}
