package com.swaglabs.swaglabs.POM.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLabsLogin {

    final String URL = "https://www.saucedemo.com/";
    WebDriver webDriver;

    @FindBy(className = "login_logo")
    WebElement loginLogo;

    @FindBy(id = "user-name")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(className = "error-message-container")
    WebElement errorMessage;

    public enum userNames {
        standard_user, locked_out_user, problem_user, performance_glitch_user
    }

    final String globalPassword = "secret_sauce";

    final String usernameErrorMessage = "Epic sadface: Username is required";

    final String passwordErrorMessage = "Epic sadface: Password is required";

    public SwagLabsLogin(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get(URL);
        PageFactory.initElements(webDriver, this);
    }

    public void visitSigninPage() {
        webDriver.get(URL);
    }

    public void setUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void setCredentials(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public void loginWithNoCredentials() {
        login("", "");
    }

    public void loginWithOnlyUserName() {
        login(userNames.standard_user.toString(), "");
    }

    public void loginWithOnlyPassword() {
        login("", globalPassword);
    }

    public void loginWithStandardUser() {
        login(userNames.standard_user.toString(), globalPassword);
    }

    public void loginWithLockedOutUser() {
        login(userNames.locked_out_user.toString(), globalPassword);
    }

    public void loginWithProblemUser() {
        login(userNames.problem_user.toString(), globalPassword);
    }

    public void loginWithGlitchUser() {
        login(userNames.performance_glitch_user.toString(), globalPassword);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void login(String username, String password) {
        setCredentials(username, password);
        clickLoginButton();
    }

    public String getError() {
        return errorMessage.getText();
    }

    public Boolean isMissingUsernameError() {
        return usernameErrorMessage.equalsIgnoreCase(getError());
    }

    public Boolean isMissingPasswordError() {
        return passwordErrorMessage.equalsIgnoreCase(getError());
    }

    public Boolean isLogoDisplayed() {
        return loginLogo.isDisplayed();
    }

    public void clearForm() {
        setCredentials("", "");
    }

    public boolean isSignedIn() {
        return webDriver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

}
