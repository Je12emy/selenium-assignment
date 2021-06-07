package com.swaglabs.swaglabs.POM.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SliderPage {
    WebDriver driver;

    @FindBy(tagName = "input")
    private WebElement slider;

    @FindBy(id = "range")
    private WebElement sliderValue;

    public SliderPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://the-internet.herokuapp.com/horizontal_slider");
        PageFactory.initElements(driver, this);
    }

    public WebElement getSlider() {
        return slider;
    }

    public String getSliderValue() {
        return sliderValue.getText();
    }

    public void assertSize(float size) {
        Assert.assertEquals(Float.parseFloat(sliderValue.getText()), size);
    }

    public void setSteps(int steps) {
        if (steps > 0) {
            sendRightArrowKey(steps);
        } else {
            sendLeftArrowKey(steps * -1);
        }
    }

    public void setStepsDragDrop(int offset) {
        Actions builder = new Actions(driver);
        builder.moveToElement(slider).click().dragAndDropBy(slider, offset, 0).build().perform();
    }

    public void sendRightArrowKey(int steps) {
        for (int i = 0; i < steps; i++) {
            slider.sendKeys(Keys.RIGHT);
        }
    }

    public void sendLeftArrowKey(int steps) {
        for (int i = 0; i < steps; i++) {
            slider.sendKeys(Keys.LEFT);
        }
    }
}
