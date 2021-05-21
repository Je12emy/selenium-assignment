package com.swaglabs.swaglabs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadObjectRepo {
    Properties p = new Properties();

    public Properties getPropertiesRepository() throws IOException {
        InputStream stream = new FileInputStream(
                new File(System.getProperty("user.dir") + "/src/main/resources/object.properties"));
        p.load(stream);
        return p;
    };
}
