package Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {

    WebDriver driver;

    public Login(WebDriver driver) {
        this.driver = driver;
    }

    //Logo
    By logos = By.xpath("//div[h1=\"Login\"]");

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
    By getStart = By.xpath("//button[@class=\"dropbtn mat-button\"]");

    public WebElement getStartedValue() {
        return driver.findElement(getStart);
    }

    //Options of started button
    By getLogin = By.xpath("//*[@ng-reflect-router-link=\"/start/login\"]");

    public WebElement getLogin() {
        return driver.findElement(getLogin);
    }

    //Options of register button

    By getRegister = By.xpath("//*[@id=\"cdk-overlay-0\"]/div/div/button[2]");

    public WebElement getRegister() {
        return driver.findElement(getRegister);
    }

    //Options of docs button
    By getDocs = By.xpath("//*[@id=\"cdk-overlay-0\"]/div/div/button[3]");

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
    By Username = By.xpath("//input[@name=\"inUser\"]");

    public WebElement getUsername() {
        return driver.findElement(Username);
    }

    By password = By.xpath("//input[@name=\"inPass\"]");

    public WebElement getPassword() {
        return driver.findElement(password);
    }

    By loginButton = By.xpath("//span[contains(text(),'Login')]");

    public WebElement getloginButton() {
        return driver.findElement(loginButton);
    }

    // Error Messages
    By UsernameError = By.xpath("//*[contains(text(),\"You need to enter a valid username\")]");

    public WebElement getUsernameError() {
        return driver.findElement(UsernameError);
    }

    By PasswordError = By.xpath("//*[contains(text(),\"You need to enter a valid password\")]");

    public WebElement getPasswordError() {
        return driver.findElement(PasswordError);
    }


}
