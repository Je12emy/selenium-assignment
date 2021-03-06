package com.swaglabs.swaglabs.utils;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIOperation {
    WebDriver driver;

    public UIOperation(WebDriver driver) {
        this.driver = driver;
    }

    public void perform(Properties p, String operation, String objectName, String objectType, String value)
            throws Exception {

        List<WebElement> items;
        switch (operation.toUpperCase()) {
            case "CLICK":
                // Perform click
                driver.findElement(this.getObject(p, objectName, objectType)).click();
                break;
            case "SETTEXT":
                // Set text on control
                driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(value);
                break;

            case "GOTOURL":
                // Get url of application
                driver.get(p.getProperty(value));
                break;
            case "GETTEXT":
                // Get text of an element
                driver.findElement(this.getObject(p, objectName, objectType)).getText();
                break;
            case "MAXIMIZE":
                // Maximize window size.
                driver.manage().window().maximize();
                break;
            case "IMPLICIT":
                // Implicitly wait
                driver.manage().timeouts().implicitlyWait(Integer.parseInt(p.getProperty(value)), TimeUnit.SECONDS);
                break;
            case "EXPLICIT":
                // Explicitly wait until web element is visible
                WebDriverWait webDriverWait = new WebDriverWait(driver, Integer.parseInt(p.getProperty(value)));
                webDriverWait.until(
                        ExpectedConditions.visibilityOfElementLocated(this.getObject(p, objectName, objectType)));
                break;
            case "ASSERT_DISPLAYED":
                Assert.assertTrue(driver.findElement(this.getObject(p, objectName, objectType)).isDisplayed());
                break;
            case "CLICK_BY_INDEX":
                items = driver.findElements(this.getObject(p, objectName, objectType));
                items.get(Integer.parseInt(p.getProperty(value))).click();
                break;
            case "ASSERT_SIZE":
                items = driver.findElements(this.getObject(p, objectName, objectType));
                Assert.assertTrue(items.size() == Integer.parseInt(p.getProperty(value)));
                break;
            case "SCREENSHOT":
                TakesScreenshot screenshot = ((TakesScreenshot) driver);
                File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
                File destination = new File(
                        System.getProperty("user.dir") + "/src/main/resources/" + p.getProperty(value));
                FileUtils.copyFile(screenshotFile, destination);
                break;
            default:
                break;
        }
    }

    /**
     * Find element BY using object type and value
     * 
     * @param p
     * @param objectName
     * @param objectType
     * @return
     * @throws Exception
     */
    private By getObject(Properties p, String objectName, String objectType) throws Exception {
        // Find by xpath
        if (objectType.equalsIgnoreCase("XPATH")) {
            return By.xpath(p.getProperty(objectName));
        }
        // find by class
        else if (objectType.equalsIgnoreCase("CLASSNAME")) {
            return By.className(p.getProperty(objectName));
        }
        // find by name
        else if (objectType.equalsIgnoreCase("NAME")) {
            return By.name(p.getProperty(objectName));
        }
        // Find by css
        else if (objectType.equalsIgnoreCase("CSS")) {
            return By.cssSelector(p.getProperty(objectName));
        }
        // find by link
        else if (objectType.equalsIgnoreCase("LINK")) {
            return By.linkText(p.getProperty(objectName));
        }
        // find by partial link
        else if (objectType.equalsIgnoreCase("PARTIALLINK")) {
            return By.partialLinkText(p.getProperty(objectName));
        }
        // find by id
        else if (objectType.equalsIgnoreCase("ID")) {
            return By.id(p.getProperty(objectName));
        } else {
            throw new Exception("Wrong object type");
        }
    }
}
