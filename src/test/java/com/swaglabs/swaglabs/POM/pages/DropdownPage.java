package com.swaglabs.swaglabs.POM.pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.swaglabs.swaglabs.utils.DriverUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DropdownPage {
    private WebDriver driver;

    @FindBy(id = "dropdown")
    private WebElement dropDownWebElement;

    private Select dropDown;

    private String dropDownValue;

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://the-internet.herokuapp.com/dropdown");
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(this.driver, this);
        dropDown = new Select(this.driver.findElement(By.id("dropdown")));
    }

    public void selectValue(int index) {
        dropDown.selectByIndex(index);
        dropDownValue = dropDown.getFirstSelectedOption().getText();
    }

    public String getSelectedValue() {
        return dropDownValue;
    }

    public void assertSelectedValue(String value) throws IOException {
        DriverUtils.takeScreenshot(driver, "dropdown.png");
        Assert.assertEquals(dropDownValue, value);
    }

}
