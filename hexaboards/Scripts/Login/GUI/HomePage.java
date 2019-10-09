package Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

    By logos = By.id("logo");

    public WebElement getLogo() {
        return driver.findElement(logos);
    }

    By home = By.xpath("//a[contains(text(),'Home')]");

    public WebElement getHomeValue() {
        return driver.findElement(home);
    }

    By About = By.xpath("//a[contains(text(),'About')]");

    public WebElement getAboutValue() {
        return driver.findElement(About);
    }

    By getStart = By.xpath("//span[contains(text(),'Getting Started')]");

    public WebElement getStartedValue() {
        return driver.findElement(getStart);
    }

    By getLogin = By.xpath("//*[contains(text(),\"Login\")]");

    public WebElement getLogin() {
        return driver.findElement(getLogin);
    }

    By getRegister = By.xpath("//*[@id=\"cdk-overlay-0\"]/div/div/button[2]");

    public WebElement getRegister() {
        return driver.findElement(getRegister);
    }

    By getDocs = By.xpath("//*[@id=\"cdk-overlay-0\"]/div/div/button[3]");

    public WebElement getDocs() {
        return driver.findElement(getDocs);
    }


    By bigLogo = By.id("home-logo");

    public WebElement getBigLogo() {
        return driver.findElement(bigLogo);
    }

    By Graph = By.id("graph");

    public WebElement getGraphlogo() {
        return driver.findElement(Graph);
    }

    By PageDesc = By.xpath("//h1[contains(text(),'This is a web page,' )]");

    public WebElement getDescriptionPage() {
        return driver.findElement(PageDesc);
    }

    By GetStartedButton = By.xpath("//span[contains(text(),'Get Started')]");

    public WebElement getStartedButton() {
        return driver.findElement(GetStartedButton);
    }

    //Button login into get started
    By login = By.xpath("//a[contains(text(),\"Log In\")]");

    public WebElement getLogins() {

        return driver.findElement(login);

    }

}
