package com.swaglabs.swaglabs.POM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsProductPage {

    SwagLabsLogin loginLabsLogin;
    final String URL = "https://www.saucedemo.com/inventory-item.html?id=4";
    WebDriver webDriver;
    @FindBy(xpath = "//div[contains(@class, 'inventory_details_name')]")
    WebElement productTitle;
    @FindBy(xpath = "//div[contains(@class, 'inventory_details_desc')]")
    WebElement productDescription;
    @FindBy(xpath = "//div[contains(@class, 'inventory_details_price')]")
    WebElement productPriceTag;
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement addToCartButton;

    public SwagLabsProductPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        loginLabsLogin = new SwagLabsLogin(webDriver);
        loginLabsLogin.loginWithStandardUser();
        PageFactory.initElements(webDriver, this);
        webDriver.get(URL);
    }

    public SwagLabsProductPage(WebDriver webDriver, Boolean signin) {
        this.webDriver = webDriver;
        loginLabsLogin = new SwagLabsLogin(webDriver);
        if (signin) {
            loginLabsLogin.loginWithStandardUser();
        }
        PageFactory.initElements(webDriver, this);
    }

    public boolean elementsAreDisplayed() {
        try {
            return productTitle.isDisplayed() && productDescription.isDisplayed() && productPriceTag.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void addToCart() {
        if (addToCartButton.getText() == "ADD TO CARD") {
            addToCartButton.click();
        }
    }

    public void removeFromCart() {
        if (addToCartButton.getText() == "REMOVE") {
            addToCartButton.click();
        }
    }

}
