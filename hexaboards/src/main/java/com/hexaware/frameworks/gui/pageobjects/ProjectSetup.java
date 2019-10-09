package com.hexaware.frameworks.gui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProjectSetup {

    WebDriver driver;

    public ProjectSetup(){}

    public ProjectSetup(WebDriver driver) {
        this.driver = driver;
    }

    //Burger button
    By burgerOptions = By.xpath("//button[@ng-reflect-message=\"Open the menu.\"]");
    public WebElement getOptionIcon() {
        return driver.findElement(burgerOptions);
    }

    //my profile option into the div of burger button
    By Profiles = By.xpath("//mat-nav-list[@role=\"navigation\"]/a[1]");
    public WebElement getMyProfiles() {
        return driver.findElement(Profiles);
    }


    //Project xpath
    By body = By.xpath("//body");
    public WebElement getBody(){return driver.findElement(body);}

    By project = By.xpath("//mat-expansion-panel-header[@id=\"mat-expansion-panel-header-0\"]");
    public WebElement getProject() {
        return driver.findElement(project);
    }

    By openButton = By.xpath("//button[@class=\"openbutton mat-raised-button mat-primary\"]/span[@class=\"mat-button-wrapper\"]");

    public WebElement getOpenButton() {
        return driver.findElement(openButton);
    }

    By changePhoto = By.xpath("//div[@class=\"divchangepho\"]/mat-icon[@role=\"img\"]");

    public WebElement getChangePhoto() {
        return driver.findElement(changePhoto);
    }

    By editDescriptionTextArea = By.xpath(" //div[@class=\"mat-form-field-infix\"]/textarea[@name=\"inDesc\"]");

    public WebElement getEditDescriptionTextArea() {
        return driver.findElement(editDescriptionTextArea);
    }

    By editDescriptionButton = By.xpath("//button[@type=\"submit\"]/span[@class=\"mat-button-wrapper\"]");

    public WebElement getEditDescriptionButton() {
        return driver.findElement(editDescriptionButton);
    }


    By addSprintButton = By.xpath("//span[contains(text(), \"Add Sprint\")]");

    public WebElement getAddSprintButton() {
        return driver.findElement(addSprintButton);
    }

    By sprintNameTextArea = By.xpath("//input[@type=\"text\"]");

    public WebElement getSprintNameTextArea() {
        return driver.findElement(sprintNameTextArea);
    }


    By createButton = By.xpath("//span[contains(text(), \"Create\")]");

    public WebElement getCreateButton() {
        return driver.findElement(createButton);
    }


    By expansionPanelSprints = By.xpath("//div[@class=\"secondrow\"]/div/div[1]/div[1]");

    public WebElement getExpansionPanelSprints() {
        return driver.findElement(expansionPanelSprints);
    }

    By goToBoardButton = By.xpath("//span[contains (text(), \"Go to Board\")]");

    public WebElement getGoToBoardButton() {
        return driver.findElement(goToBoardButton);
    }

    By expansionPanelMembers = By.xpath("//mat-expansion-panel-header[@id=\"mat-expansion-panel-header-1\"]");

    public WebElement getExpansionPanelMembers() {
        return driver.findElement(expansionPanelMembers);
    }

    By statsButton = By.xpath("//span[contains (text(), \"Stats\")]");

    public WebElement getStatsButton() {
        return driver.findElement(statsButton);
    }


    By editRoleButton = By.xpath("//div[@id=\"cdk-accordion-child-1\"]/div/div/button//span[contains(text(),\"Edit Role\")]");

    public WebElement getEditRoleButton() {
        return driver.findElement(editRoleButton);
    }

    By roleList = By.xpath("//select[@id=\"mat-input-1\"]");
    public WebElement  getRoleList(){return driver.findElement(roleList);}

    By findUser = By.xpath("//div/input[@placeholder=\"Search by username.\"]");
    public WebElement getFindUser(){return driver.findElement(findUser);}

    By addButton = By.xpath("//button[@class=\"addbutton mat-raised-button mat-primary\"]/span");
    public WebElement getAddButton(){return  driver.findElement(addButton);}

    By editRoleList = By.xpath("//select[@ng-reflect-form=\"[object Object]\"]");
    public WebElement getEditRoleList() {
        return driver.findElement(editRoleList);
    }

    By saveButtonEditRole = By.xpath("//span[contains (text(), \"Save\")]");

    public WebElement getSaveButtonEditRole() {
        return driver.findElement(saveButtonEditRole);
    }

    By deleteButtonMember = By.xpath("//div[@id=\"cdk-accordion-child-1\"]/div/div/button[3]/span[contains(text(),\"Delete\")]");
    public WebElement getDeleteButtonMember() {
        return driver.findElement(deleteButtonMember);
    }

    By addMemberButton = By.xpath("//span[contains(text(), \"Add Member\")]");

    public WebElement getAddMemberButton() {
        return driver.findElement(addMemberButton);
    }

    public WebElement getPanel(String panel) {
        return driver.findElement(By.xpath("//div[@class=\"expan\"]//*[contains(text(),\"" + panel + "\")]"));
    }
}
