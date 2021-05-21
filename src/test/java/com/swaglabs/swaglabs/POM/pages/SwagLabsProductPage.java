package com.swaglabs.swaglabs.POM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsProductPage {

    SwagLabsLogin loginLabsLogin;
    final String ADD_TO_CART = "ADD TO CART";
    final String REMOVE_FROM_CART = "REMOVE";

    final String URL = "https://www.saucedemo.com/inventory-item.html?id=4";
    WebDriver webDriver;
    @FindBy(xpath = "//div[contains(@class, 'inventory_details_name')]")
    WebElement productTitle;
    @FindBy(xpath = "//div[contains(@class, 'inventory_details_desc')]")
    WebElement productDescription;
    @FindBy(xpath = "//div[contains(@class, 'inventory_details_price')]")
    WebElement productPriceTag;
    @FindBy(xpath = "//button[contains(@class, 'btn_inventory')]")
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
        if (webDriver.getCurrentUrl().equals(URL)) {
            return productTitle.isDisplayed() && productDescription.isDisplayed() && productPriceTag.isDisplayed();
        }
        return false;
    }

    public void addToCart() {
        if (addToCartButton.getText().equals(ADD_TO_CART)) {
            addToCartButton.click();
        }
    }

    public void removeFromCart() {
        if (addToCartButton.getText().equals(REMOVE_FROM_CART)) {
            addToCartButton.click();
        }
    }

    public Boolean isAddedtoCart() {
        return addToCartButton.getText().equals(REMOVE_FROM_CART);
    }

    public Boolean isNotOnCart() {
        return addToCartButton.getText().equals(ADD_TO_CART);
    }

}
