package com.nathan.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends com.nathan.pages.BasePage {

    @FindBy(id = "user-name") private WebElement usernameInput;   // FIX
    @FindBy(id = "password")  private WebElement passwordInput;   // FIX
    @FindBy(id = "login-button") private WebElement loginButton;  // FIX
    @FindBy(css = "[data-test='error']") private WebElement errorMsg; // FIX

    public LoginPage(org.openqa.selenium.WebDriver driver){
        super(driver);
    }

    public void enterUsername(String u){
        usernameInput.clear();
        usernameInput.sendKeys(u);
    }

    public void enterPassword(String p){
        passwordInput.clear();
        passwordInput.sendKeys(p);
    }

    public void clickLogin(){
        loginButton.click();
    }

    public String getErrorText(){
        return errorMsg.getText();
    }
}
