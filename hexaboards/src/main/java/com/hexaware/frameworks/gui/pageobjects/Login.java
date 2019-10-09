package com.hexaware.frameworks.gui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {

    WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    //logo of login
    By logos = By.id("logo");

    public WebElement getLogo() {
        return driver.findElement(logos);
    }

    //Home button
    By home = By.xpath("//a[contains(text(),'Home')]");

    public WebElement getHomeValue() {
        return driver.findElement(home);
    }

    //About Button
    By About = By.xpath("//a[contains(text(),'About')]");

    public WebElement getAboutValue() {
        return driver.findElement(About);
    }

    //Getting Started Button
    By getStart = By.xpath("//span[contains(text(),'Getting Started')]");

    public WebElement getStartedValue() {
        return driver.findElement(getStart);
    }

    //Login into getting started
    By getLogin = By.xpath("//*[@routerlink=\"/start/login\"]");

    public WebElement getLogin() {
        return driver.findElement(getLogin);
    }

    //Register into gettin started
    By getRegister = By.xpath("//button[@routerlink=\"/start/register\"]");

    public WebElement getRegister() {
        return driver.findElement(getRegister);
    }

    //Options of docs button into getting started
    By getDocs = By.xpath("//button[@routerlink=\"/start/documentation\"]");

    public WebElement getDocs() {
        return driver.findElement(getDocs);
    }

    //Login title
    By loginName = By.xpath("//*[@id=\"outer-div\"]/div[1]/h1");

    public WebElement getLoginName() {
        return driver.findElement(loginName);
    }

    //google icon
    By google = By.xpath("//img[@src=\"../../assets/img/google.png\"]");

    public WebElement getGoogleIcon() {
        return driver.findElement(google);
    }

    //register button
    By register = By.xpath("//a[contains(text(),\"Register\")]");

    public WebElement registerButton() {
        return driver.findElement(register);
    }

    /////////////////////////////////fields objects
    //Field the name
    By Username = By.xpath("//input[@name=\"inUser\"]");

    public WebElement getUsername() {
        return driver.findElement(Username);
    }

    //Field the password
    By password = By.xpath("//input[@name=\"inPass\"]");

    public WebElement getPassword() {
        return driver.findElement(password);
    }

    //Button login
    By loginButton = By.xpath("//span[contains(text(),'Login')]");

    public WebElement getloginButton() {
        return driver.findElement(loginButton);
    }

    // Error Messages
    By UsernameError = By.xpath("//*[contains(text(),\"You need to enter a valid username\")]");
    public WebElement getUsernameError() {
        return driver.findElement(UsernameError);
    }
    //Error Password
    By PasswordError = By.xpath("//*[contains(text(),\"You need to enter a valid password\")]");
    public WebElement getPasswordError() {
        return driver.findElement(PasswordError);
    }
    //Forgot your username?
    By ForgotUsername = By.xpath("//a[contains(text(),'Forgot your username?')]");
    public WebElement getForgotUsername() {
        return driver.findElement(ForgotUsername);
    }
    //Forgot your password?
    By ForgotPassword = By.xpath("//a[contains(text(),'Forgot your password?')]");
    public WebElement getForgotPassword() {
        return driver.findElement(ForgotPassword);
    }

}
