package com.hexaware.frameworks.gui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    ////////////////////////////////////////////////////////////////////////////////////
    //Logo
    By logos = By.id("logo");
    public WebElement getLogo() {
        return driver.findElement(logos);
    }
    //Button home
    By homeOption = By.xpath("//a[contains(text(),'Home')]");
    public WebElement getHomeValue() {
        return driver.findElement(homeOption);
    }
    //Button about
    By aboutOption = By.xpath("//a[contains(text(),'About')]");
    public WebElement getAboutValue() {
        return driver.findElement(aboutOption);
    }
    //Button getting started
    By gettingStartedOption = By.xpath("//span[contains(text(),'Getting Started')]");
    public WebElement getStartedValue() {
        return driver.findElement(gettingStartedOption);
    }
    //Login into getting started
    By gettingLogin = By.xpath("//*[@id=\"cdk-overlay-0\"]/div/div/button[1]");
    public WebElement getLogin() {
        return driver.findElement(gettingLogin);
    }
    //Register into getting started
    By gettingRegister = By.xpath("//*[@id=\"cdk-overlay-0\"]/div/div/button[2]");
    public WebElement getRegister() {
        return driver.findElement(gettingRegister);
    }
    //Docs into getting started
    By gettingDocs = By.xpath("//*[@id=\"cdk-overlay-0\"]/div/div/button[3]");
    public WebElement getDocs() {
        return driver.findElement(gettingDocs);
    }
    //Big Logo
    By bigLogo = By.id("home-logo");
    public WebElement getBigLogo() {
        return driver.findElement(bigLogo);
    }
    //Graph logo
    By graph = By.id("graph");
    public WebElement getGraphlogo() {
        return driver.findElement(graph);
    }
    //This is a web page
    By pageDesc = By.xpath("//h1[contains(text(),'This is a web page,' )]");
    public WebElement getDescriptionPage() {
        return driver.findElement(pageDesc);
    }
    //Get started button to the bottom
    By getStartedButton = By.xpath("//span[contains(text(),'Get Started')]");
    public WebElement getStartedButton() {
        return driver.findElement(getStartedButton);
    }

    //Button login into get started
    By loginOption = By.xpath("//a[contains(text(),\"Log In\")]");
    public WebElement getLogins() {
        return driver.findElement(loginOption);
    }
}
