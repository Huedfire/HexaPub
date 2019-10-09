package com.hexaware.frameworks.gui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoardPage2 {
    WebDriver driver;

    public BoardPage2(WebDriver driver) {
        this.driver = driver;
    }
    ////////////////////////////Page Objects/////////////////////////////////////////////

    //Settings icon
    By settingsIcon = By.xpath("//body");
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

    //click on sprint
    By sprint1 = By.xpath("//mat-expansion-panel-header[@id=\"mat-expansion-panel-header-3\"]");
    public WebElement getSprint1(){ return driver.findElement(sprint1);  }

    //click on specific sprint
    public WebElement getPanel(String panel) {
        return driver.findElement(By.xpath("//div[@class=\"expan\"]//*[contains(text(),\"" + panel + "\")]"));
    }

    //Go to board button
    By GoToBtn = By.xpath("//div[@class=\"expan\"]//div[@style=\"visibility: visible;\"]//button");
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

    //////////////////////////Board Objects///////////////////////////////////////////////////////////////

    //First task backlog
    By firstBacklog = By.xpath("//*[@id=\"cdk-drop-list-0\"]/div[1]");
    public WebElement getFirstBacklog(){ return driver.findElement(firstBacklog); }

    //first taskToDo
    By firstToDo = By.xpath("//*[@id=\"cdk-drop-list-1\"]/div[1]");
    public WebElement getFirstToDo() { return driver.findElement(firstToDo); }

    //Logo Backlog
    By backlogLogo = By.xpath("//*[contains(text(),\"Backlog\")]");
    public WebElement getBacklog() {
        return driver.findElement(backlogLogo);
    }

    //Logo TO Do
    By toDoLogo = By.xpath("//*[contains(text(),\"To Do\")]");
    public WebElement getToDoLogo() {
        return driver.findElement(toDo);
    }


    By toDo = By.xpath("//*[@id=\"cdk-drop-list-1\"]");
    public WebElement getToDo() {
        return driver.findElement(toDo);
    }


    //Logo In Progress
    By progress = By.xpath("//*[contains(text(),\"In Progress\")]");
    public WebElement getProgress() {
        return driver.findElement(progress);
    }

    //Logo Test
    By test = By.xpath("//*[contains(text(),\"Test\")]");
    public WebElement getTest() {
        return driver.findElement(test);
    }


    //Logo Review
    By review = By.xpath("//*[contains(text(),\"Review\")]");
    public WebElement getReviewLogo() {
        return driver.findElement(review);
    }

    //Logo Done
    By done = By.xpath("//*[contains(text(),\"Done\")]");
    public WebElement getDoneLogo() {
        return driver.findElement(done);
    }

    //Button Add Story
    By story = By.xpath("//*[contains(text(),\"Add Story\")]");
    public WebElement getAddStoryButton() {
        return driver.findElement(story);
    }

    //Button End Sprint
    By sprint = By.xpath("//*[contains(text(),\"End Sprint\")]");
    public WebElement getEndSprintButton() {
        return driver.findElement(sprint);
    }

    //Backlog field
    By backlogField = By.xpath("//*[@ng-reflect-id=\"cdk-drop-list-0\"]");
    public WebElement getBacklogField() {
        return driver.findElement(backlogField);
    }

    //To Do field
    By toDoField = By.xpath("//*[@ng-reflect-id=\"cdk-drop-list-1\"]");
    public WebElement getToDoField() {
        return driver.findElement(toDoField);
    }

    //In Progress field
    By inProgressField = By.xpath("//*[@ng-reflect-id=\"cdk-drop-list-2\"]");
    public WebElement getInProgressField() {
        return driver.findElement(inProgressField);
    }

    //Test field
    By testField = By.xpath("//*[@ng-reflect-id=\"cdk-drop-list-3\"]");
    public WebElement getTestField() {
        return driver.findElement(testField);
    }

    //Review field
    By reviewField = By.xpath("//*[@ng-reflect-id=\"cdk-drop-list-4\"]");
    public WebElement getReviewField() {
        return driver.findElement(reviewField);
    }

    //Done field
    By doneField = By.xpath("//*[@ng-reflect-id=\"cdk-drop-list-5\"]");
    public WebElement getDoneField() {
        return driver.findElement(doneField);
    }

    By GoToBtn3 = By.xpath(" //div[@class=\"expan\"]//div[@style=\"visibility: visible;\"]//button");
    public WebElement getGoToBtn3(){ return driver.findElement(GoToBtn3); }

}
