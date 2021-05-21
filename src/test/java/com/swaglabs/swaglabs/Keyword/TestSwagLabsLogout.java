package com.swaglabs.swaglabs.Keyword;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

import com.swaglabs.swaglabs.utils.ReadExcel;
import com.swaglabs.swaglabs.utils.ReadObjectRepo;
import com.swaglabs.swaglabs.utils.UIOperation;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSwagLabsLogout {

    WebDriver driver;
    ReadExcel readExcel;
    ReadObjectRepo objectRepo;
    Properties properties;
    String path;
    UIOperation operation;

    @BeforeTest
    public void setup() throws URISyntaxException {
        driver = new FirefoxDriver();
        readExcel = new ReadExcel();
        objectRepo = new ReadObjectRepo();
        URL res = getClass().getClassLoader().getResource("TestWorkbook.xlsx");
        File file = Paths.get(res.toURI()).toFile();
        path = file.getAbsolutePath();
        operation = new UIOperation(driver);
    }

    @Test(description = "Signs in and Logs out from application.")
    public void test_logout_from_site() throws Exception {
        properties = objectRepo.getPropertiesRepository();
        Sheet testSheet = readExcel.readExcel(path, "TestWorkBook.xlsx", "Logout");
        readExcel.executeScript(testSheet, operation, properties);

    }
}
