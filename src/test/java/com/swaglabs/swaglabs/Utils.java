package com.swaglabs.swaglabs;

public class Utils {
    public static void setDriver() {
        String driverPath = "/geckodriver";
        System.setProperty("webdriver.gecko.driver", driverPath);
    }
}
