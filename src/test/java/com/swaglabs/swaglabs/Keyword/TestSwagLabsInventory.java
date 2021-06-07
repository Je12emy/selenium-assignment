package com.swaglabs.swaglabs.Keyword;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

import com.swaglabs.swaglabs.utils.DriverUtils;
import com.swaglabs.swaglabs.utils.ReadExcel;
import com.swaglabs.swaglabs.utils.ReadObjectRepo;
import com.swaglabs.swaglabs.utils.UIOperation;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSwagLabsInventory {
    WebDriver driver;
    ReadExcel readExcel;
    ReadObjectRepo objectRepo;
    Properties properties;
    String path;
    UIOperation operation;

    @BeforeTest(alwaysRun = true)
    public void setup() throws URISyntaxException {
		DriverUtils.setDriver();
        driver = new FirefoxDriver();
        readExcel = new ReadExcel();
        objectRepo = new ReadObjectRepo();
        URL res = getClass().getClassLoader().getResource("TestWorkbook.xlsx");
        File file = Paths.get(res.toURI()).toFile();
        path = file.getAbsolutePath();
        operation = new UIOperation(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test(description ="Signs in, adds a new item to shopping cart and checks it in the checkout page")
    public void test_add_item_to_cart_and_verify() throws Exception {
        properties = objectRepo.getPropertiesRepository();
        Sheet testSheet = readExcel.readExcel(path, "TestWorkBook.xlsx", "Add to Cart");
        readExcel.executeScript(testSheet, operation, properties);
    }
}
