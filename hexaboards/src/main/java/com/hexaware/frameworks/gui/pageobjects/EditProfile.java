package com.hexaware.frameworks.gui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditProfile {
    WebDriver driver;

    public EditProfile(WebDriver driver) {
        this.driver = driver;
    }

    By profileButton = By.xpath("//mat-nav-list[@role=\"navigation\"]/a[1]");
    public WebElement getProfileButton() {
        return driver.findElement(profileButton);
    }

    By menuButton = By.xpath("//button[@mattooltip=\"Open the menu.\"]");

    public WebElement getMenuButton() {
        return driver.findElement(menuButton);
    }

    By profilePicture = By.xpath("//h1[@mattooltip=\"That should be you. Right?\"]");

    public WebElement getProfilePicture() {
        return driver.findElement(profilePicture);
    }

    //Unlock and lock name field into  my profile
    By unlockName = By.xpath("//span[contains(text(),\"Unlock/lock name\")]");
    public WebElement getUnlockName() {
        return driver.findElement(unlockName);
    }

    //Unlock and lock email field into my profile
    By unlockEmail = By.xpath("//Span[contains(text(),\"Unlock/lock e-mail\")]");
    public WebElement getUnlockEmail() {
        return driver.findElement(unlockEmail);
    }

    //field the name
    By editName = By.xpath("//input[@name=\"inName\"]");
    public WebElement getEditName() {
        return driver.findElement(editName);
    }

    //field the e-mail
    By editEmail = By.xpath("//input[@name=\"inMail\"]");
    public WebElement getEditEmail() {
        return driver.findElement(editEmail);
    }

    // Username in the top
    By username = By.id("id=\"usernameh4\"");
    public WebElement getUsername(){
        return driver.findElement(username);
    }

    //Save button into my profile
    By buttonSave = By.xpath("//*[@id=\"fulldialog\"]/form/div[4]/button/span");
    public WebElement getSaveButton() {
        return driver.findElement(buttonSave);
    }

    public String getNameVerified2() {
        String str = driver.findElement(By.xpath("//input[@name=\"inName\"]")).getAttribute("ng-reflect-model");
        return str;
    }

    public String getEmailVerified2() {
        String str = driver.findElement(By.xpath("//input[@name=\"inMail\"]")).getAttribute("ng-reflect-model");
        return str;
    }

    //Button change photo into my profile
    By photo = By.id("changephoto");
    public WebElement getChangePhoto() {
        return driver.findElement(photo);
    }

    // "X" button close my profile
    By close = By.xpath("//*[@id=\"title-div\"]/div/button");
    public WebElement getCloseEdit()
    {
        return driver.findElement(close);
    }

    //Change password link
    By password = By.xpath("//a[h4]");
    public WebElement getClickPassword()
    {
        return driver.findElement(password);
    }

    //Type new password field
    By newPassword = By.xpath("//*[@name=\"inPass\"]");
    public WebElement getNewPassword()
    {
        return driver.findElement(newPassword);
    }

    //Type new password field
    By newBtnSave = By.id("savebtn");
    public WebElement getNewBtnSave(){ return driver.findElement(newBtnSave); }

    //Error
    By blankErrorName = By.xpath("//div[@fxlayout=\"column\"]/mat-form-field[1]/div/div[3]/div/mat-error");
    public WebElement getBlankErrorName() { return driver.findElement(blankErrorName);    }

    By blankErrorEmail = By.xpath("//div[@fxlayout=\"column\"]/mat-form-field[2]/div/div[3]/div/mat-error");
    public WebElement getBlankErrorEmail()
    {
        return driver.findElement(blankErrorEmail);
    }

    By logOut = By.xpath("//span[@mattooltip=\"Terminate your current session.\"]");
    public WebElement getLogOut()    {        return driver.findElement(logOut);    }

    //Burger button
    By burgerOptions = By.xpath("//button[@ng-reflect-message=\"Open the menu.\"]");
    public WebElement getBurgerIcon() {
        return driver.findElement(burgerOptions);
    }

    //error message new password
    By passError = By.xpath("//div[@class=\"alldiv\"]//mat-error");
    public WebElement getPassError() {
        return driver.findElement(passError);
    }

    //------------------------------------User Deletion -------------------------------------------------
    ////////delete button///////
    By deleteButton = By.id("deletebtn");
    public WebElement getDeleteButton(){return driver.findElement(deleteButton);}

    //////field the password//////
    By fieldpwd = By.xpath("//div/input[@ng-reflect-type=\"password\"]");
    public WebElement getFieldPwd(){return driver.findElement(fieldpwd);}

    ////////delete after field the password/////
    By deleteButton2 = By.xpath("//button[@id=\"savebtn\"]/span");
    public WebElement getDeleteButton2(){return driver.findElement(deleteButton2);}



}
