package com.hexaware.frameworks.gui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.InputStream;

public class ProfileInformation {
    WebDriver driver;

    public ProfileInformation(WebDriver driver) {
        this.driver = driver;
    }


    By menuButton = By.xpath("//button[@mattooltip=\"Open the menu.\"]");

    public WebElement getMenuButton() {
        return driver.findElement(menuButton);
    }


    //ERROR EN BURGUER BUTTON
    By burguerButton = By.xpath("//button[@mattooltip=\"Open the menu.\"]");
    public WebElement getBurguerButton() {
        return driver.findElement(burguerButton);
    }

    By menuOptions = By.xpath("//div[@class=\"mat-drawer-inner-container\"]");

    public WebElement getMenuOptions() {
        return driver.findElement(menuOptions);
    }


    By profileButton = By.xpath("//mat-nav-list[@role=\"navigation\"]/a[1]");

    public WebElement getProfileButton() {
        return driver.findElement(profileButton);
    }


    By profilePicture = By.xpath("//h1[@class=\"user-name\"]");

    public WebElement getProfilePicture() {
        return driver.findElement(profilePicture);
    }

    By profileWindow = By.xpath("//div[@dir=\"ltr\"]/div");

    public WebElement getProfileWindow() {
        return driver.findElement(profileWindow);
    }


    By windowsTitle = By.xpath("//*[@id=\"title-div\"]/h1");

    public WebElement getWindowsTitle() {
        return driver.findElement(windowsTitle);
    }


    By closeButton = By.xpath("//button/span[contains(text(),\"X\")]");

    public WebElement getCloseButton() {
        return driver.findElement(closeButton);
    }


    public WebElement getVerifiedName(String nombre) {
        return driver.findElement(By.xpath("//*[@ng-reflect-model='"+ nombre +"']"));
    }

    public WebElement getVerifiedEmail(String email) {
        return driver.findElement(By.xpath("//*[@ng-reflect-model='"+ email +"']"));
    }

}
