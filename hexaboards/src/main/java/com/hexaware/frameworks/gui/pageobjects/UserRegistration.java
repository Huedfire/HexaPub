package com.hexaware.frameworks.gui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserRegistration {
    WebDriver driver;
    public UserRegistration(WebDriver driver) {
        this.driver = driver;
    }

    //Logo
    By logos = By.id("logo");
    public WebElement getLogo() {
        return driver.findElement(logos);
    }
    //Home button on the top
    By home = By.xpath("//a[contains(text(),'Home')]");
    public WebElement getHomeValue() {
        return driver.findElement(home);
    }
    //About Button on the top
    By About = By.xpath("//a[contains(text(),'About')]");
    public WebElement getAboutValue() {
        return driver.findElement(About);
    }
    //Getting Started Button on the top
    By getStart = By.xpath("//span[contains(text(),'Getting Started')]");
    public WebElement getStartedValue() {
        return driver.findElement(getStart);
    }
    //Options of started button into getting started
    By getLogin = By.xpath("//button[@ng-reflect-router-link=\"/start/login\"]");
    public WebElement getLogin() {
        return driver.findElement(getLogin);
    }
    //Options of register button into getting started
    By getRegister = By.xpath("//button[@ng-reflect-router-link=\"/start/register\"]");
    public WebElement getRegister() {
        return driver.findElement(getRegister);
    }
    //Options of docs button into getting started
    By getDocs = By.xpath("//button[@ng-reflect-router-link=\"/start/documentation\"]");
    public WebElement getDocs() {
        return driver.findElement(getDocs);
    }
    //Register with google
    By registerG = By.xpath("//a[@id='google-reg']");
    public WebElement getRegisterG() {
        return driver.findElement(registerG);
    }
    //image of google
    By googleS = By.xpath(" //img[@src=\"../../assets/img/google.png\"]");
    public WebElement getNavigate() {
        return driver.findElement(googleS);
    }
    // Login button
    By login = By.xpath("//a[contains(text(),\"Log In\")]");
    public WebElement getLogins() {
        return driver.findElement(login);
    }
    /////////////////////////////////////Field Objects/////////////////////////////////////////
//Field the name
    By nameLocator = By.xpath("//input[@name=\"inName\"]");
    public WebElement getName() {
        return driver.findElement(nameLocator);
    }
    //Field the e-mail
    By emailLocator = By.xpath("//input[@name=\"inEmail\"]");
    public WebElement getEmail() {
        return driver.findElement(emailLocator);
    }
    //Field the username
    By usernameLocator = By.xpath("//input[@name=\"inUser\"]");
    public WebElement getUsername() {
        return driver.findElement(usernameLocator);
    }
    //Field the password
    By passwordLocator = By.xpath("//input[@name=\"inPass\"]");
    public WebElement getPassword() {
        return driver.findElement(passwordLocator);
    }
    //Field confirm the password
    By confirmPasswordLocator = By.xpath("//input[@name=\"confPass\"]");
    public WebElement getConfirmPassword() {
        return driver.findElement(confirmPasswordLocator);
    }
    //checkbox of I agree to the Terms of use & service.
    By termsLocator = By.xpath("//input[@id=\"mat-checkbox-1-input\"]");
    public WebElement getTermsLocator() {
        return driver.findElement(termsLocator);
    }
    //Create an account
    By createButton = By.xpath("//*[@class=\"mat-raised-button\"]");
    public WebElement getButtonCreate() {
        return driver.findElement(createButton);
    }
    By createButtonCSS = By.cssSelector("#inner-div > button");
    public WebElement getButtonCreateCSS() {
        return driver.findElement(createButtonCSS);
    }
    By loginText = By.xpath("//*[@id=\"outer-div\"]/div[1]/h1");
    public WebElement getLoginText() {
        return driver.findElement(loginText);
    }

    ////////////////////////////////////Error Messages/////////////////////////////////////////////
    By nameError = By.xpath("//*[contains(text(),\"You need to enter a name\")]");
    public WebElement getNameError() {
        return driver.findElement(nameError);
    }
    By emailError = By.xpath("//*[contains(text(),\"You need to enter a valid E-mail.\")]");
    public WebElement getemailError() {
        return driver.findElement(emailError);
    }
    By usernameError = By.xpath("//*[contains(text(),\"You need to enter a valid username. Lenght: 4-15 characters.\")]");
    public WebElement getusernameError() {
        return driver.findElement(usernameError);
    }
    By passwordError = By.xpath("//*[contains(text(),\"You need to enter a valid password. Lenght: 4-20 characters.\")]");
    public WebElement getPasswordError() {
        return driver.findElement(passwordError);
    }
    By confPassError = By.xpath("//*[contains(text(),\"Invalid password.\")]");
    public WebElement getConfPassError() {
        return driver.findElement(confPassError);
    }

}
