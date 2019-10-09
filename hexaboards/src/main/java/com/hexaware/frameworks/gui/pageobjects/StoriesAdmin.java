package com.hexaware.frameworks.gui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StoriesAdmin {
    WebDriver driver;

    public StoriesAdmin(WebDriver driver){ this.driver = driver; }

    ////////////////////////////Page Objects/////////////////////////////////////////////

    //Settings icon
    By settingsIcon = By.xpath("//mat-icon[@id=\"settings-icon\"]");
    public WebElement getSettingsIcon() {
        return driver.findElement(settingsIcon);
    }

    //my profile option
    By myProfile = By.xpath("//img[@alt=\"user-photo\"]");
    public WebElement getMyProfile() {
        return driver.findElement(myProfile);
    }

    //expansion panel, all projects
    By expansionPanel = By.xpath("//mat-expansion-panel-header[@id=\"mat-expansion-panel-header-0\"]");
    public WebElement getExpansionPanel() {
        return driver.findElement(expansionPanel);
    }

    // Options of the burger button when the user click
    //Burger button
    By burgerOptions = By.xpath("//button[@mattooltip='Open the menu.']");
    public WebElement getOptionIcon() {
        return driver.findElement(burgerOptions);
    }

    //Options of the burger button
    By options = By.xpath("//mat-sidenav[@tabindex=\"-1\"]/div");
    public WebElement getMyOptions() {
        return driver.findElement(options);
    }

    //my profile option
    By profiles = By.xpath("//mat-nav-list[@role=\"navigation\"]/a[1]");
    public WebElement getMyProfiles() {
        return driver.findElement(profiles);
    }

    //Sprint label
    By sprintLabel = By.xpath("//*[contains(text(),\"Sprints\")]");
    public WebElement getSprintLabel(){ return driver.findElement(sprintLabel);}

    //click on sprint1
    By sprint1 = By.xpath("//*[contains(text(), \"Sprint @\")]");
    public WebElement getSprint1(){ return driver.findElement(sprint1);  }

    //Go to board button
    By GoToBtn = By.xpath("//div[@id=\"cdk-accordion-child-3\"]/div/div/button/span");
    public WebElement getGoToBtn(){ return driver.findElement(GoToBtn); }

    //Inprogress div
    By InProgDiv = By.xpath("//*[@id=\"cdk-drop-list-2\"]");
    public WebElement getInProgDiv(){ return driver.findElement(InProgDiv); }

    //First In progress
    By firstInProg = By.xpath("//*[@id=\"cdk-drop-list-2\"]/div[1]");
    public WebElement getFirstInProg(){ return driver.findElement(firstInProg); }

    //Review div
    By reviewDiv = By.xpath("//*[@id=\"cdk-drop-list-4\"]");
    public WebElement getReviewDiv(){ return driver.findElement(reviewDiv); }

    //first Review
    By firstReview = By.xpath("//*[@id=\"cdk-drop-list-4\"]/div[1]");
    public WebElement getFirstReview(){ return driver.findElement(firstReview); }

    //done div
    By doneDiv = By.xpath("//*[@id=\"cdk-drop-list-5\"]");
    public WebElement getDoneDiv() { return driver.findElement(doneDiv); }

    //first done
    By firstDone = By.xpath("//*[@id=\"cdk-drop-list-5\"]/div[1]");
    public WebElement getFirstDone() { return driver.findElement(firstDone);}

    //Test div
    By testDiv = By.xpath("//*[@id=\"cdk-drop-list-3\"]");
    public WebElement getTestDiv() { return driver.findElement(testDiv); }

    //firs test
    By firstTest = By.xpath("//*[@id=\"cdk-drop-list-3\"]/div[1]");
    public WebElement getFirstTest() { return driver.findElement(firstTest); }

    //open button
    By openButton = By.xpath("//button[@class=\"openbutton mat-raised-button mat-primary\"]/span[@class=\"mat-button-wrapper\"]");
    public WebElement getOpenButton() {
        return driver.findElement(openButton);
    }

    //my projects option
    By myProjects = By.xpath("//mat-nav-list[@role=\"navigation\"]/a[2]");
    public WebElement getMyProject() {
        return driver.findElement(myProjects);
    }

    //settings option
    By settings = By.xpath("//mat-icon[@aria-describedby=\"cdk-describedby-message-9\"]");
    public WebElement getSettings() {
        return driver.findElement(settings);
    }

    //sign out option
    By signOut = By.xpath("//mat-nav-list[@role=\"navigation\"]/a[4]");
    public WebElement getSignOut() {
        return driver.findElement(signOut);
    }

    //newproject button option
    By newProjectButton = By.xpath("//button[@mattooltip=\"Create a new project!\"]");
    public WebElement getNewProjectBar() {
        return driver.findElement(newProjectButton);
    }

    //Button Add Story
    By story = By.xpath("//*[contains(text(),\"Add Story\")]");
    public WebElement getAddStoryButton() {
        return driver.findElement(story);
    }

    //sprint 2
    By Sprint2 = By.xpath("//mat-expansion-panel-header[@id=\"mat-expansion-panel-header-3\"]");
    public WebElement getSprint2() {
        return driver.findElement(Sprint2);
    }

    //create story
    By createStory = By.xpath("//*[contains(text(), \"Create Story\")]");
    public WebElement getCreateStoryBtn(){ return driver.findElement(createStory); }


    //enter a story div
    By enterStory = By.xpath("//*[@name=\"inStory\"]");
    public WebElement getEnterStory(){ return driver.findElement(enterStory); }

    //edit story
    public WebElement getEditStory(String story) {
        return driver.findElement(By.xpath("//*[contains(text(),\""+story+"\")]"));
    }

    //label edit story
    public WebElement getLabelEditStory(){
        return driver.findElement(By.xpath("//*[contains(text(),\"Enter the new story here\")]"));
    }

    //edit story button

    public WebElement getEditStoryBtn(){
        return driver.findElement(By.xpath("//*[contains(text(),\"Edit Story\")]"));
    }

    //div edit text
    public WebElement getEditTextDiv(){
        return driver.findElement(By.xpath("//*[@name=\"inStory\"]"));
    }

    //click on specific sprint
    public WebElement getPanel(String panel) {
        return driver.findElement(By.xpath("//div[@class=\"expan\"]//*[contains(text(),\""+panel+"\")]"));
    }

    By GoToBtn3 = By.xpath(" //div[@class=\"expan\"]//div[@style=\"visibility: visible;\"]//button");
    public WebElement getGoToBtn3(){ return driver.findElement(GoToBtn3); }

}


