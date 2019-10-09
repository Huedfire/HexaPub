package com.hexaware.frameworks.gui.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProjectCreation {

    WebDriver driver;
    public ProjectCreation(WebDriver driver) {
        this.driver = driver;
    }
    ///////////Page objects///////////////////////

    //Settings icon on the top right
    By settingsIcon = By.xpath("//mat-icon[@id=\"settings-icon\"]");
    public WebElement getSettingsIcon() {
        return driver.findElement(settingsIcon);
    }


    //my profile option (photo)
    By MyProfile = By.xpath("//img[@alt=\"user-photo\"]");
    public WebElement getMyProfile() {
        return driver.findElement(MyProfile);
    }


//////////////////////////////////////////////////////// Options of burger button/////////////////////////////////////////////////

    //Burger button
    By burgerOptions = By.xpath("//button[@ng-reflect-message=\"Open the menu.\"]");
    public WebElement getOptionIcon() {
        return driver.findElement(burgerOptions);
    }

    //All div of burger button
    By Options = By.xpath("//mat-sidenav[@tabindex=\"-1\"]/div");
    public WebElement getMyOptions() {
        return driver.findElement(Options);
    }

    //my profile option into the div of burger button
    By Profiles = By.xpath("//mat-nav-list[@role=\"navigation\"]/a[1]");
    public WebElement getMyProfiles() {
        return driver.findElement(Profiles);
    }


    //my projects option into the burger button
    By MyProjects = By.xpath("//mat-nav-list[@role=\"navigation\"]/a[2]");
    public WebElement getMyProject() {
        return driver.findElement(MyProjects);
    }

    //settings option into the burger button
    By Settings = By.xpath("//mat-nav-list[@role=\"navigation\"]/a[3]");
    public WebElement getSettings() {
        return driver.findElement(Settings);
    }

    //sign out option into the burger button
    By SignOut = By.xpath("//mat-nav-list[@role=\"navigation\"]/a[4]");
    public WebElement getSignOut() {
        return driver.findElement(SignOut);
    }

    //newproject button option into the burger button
    By newProjectB = By.xpath("//button[@class=\"btnproject mat-raised-button mat-primary\"]");
    public WebElement getNewProjectBar() {
        return driver.findElement(newProjectB);
    }

    /////////////////////////Fields objects to create new project  /////////////////////////////////////////////////////
    // Create a new project button
    By newProject = By.xpath("//a[contains(text(),\"new project\")]");
    public WebElement getNewProject() {
        return driver.findElement(newProject);
    }

    //name label
    By namelabel = By.xpath("//h3[contains(text(),\"Name\")]");
    public WebElement getNameLabel() {
        return driver.findElement(namelabel);
    }

    // Name field
    By name = By.xpath("//input[@name=\"inName\"]");
    public WebElement getName() {
        return driver.findElement(name);
    }

    // Name field length
    public String getMaxLengthName() {
        String str = driver.findElement(By.xpath("//input[@name=\"inName\"]")).getAttribute("maxlength");
        return  str;
    }
    public String getMinLengthName() {
        String str = driver.findElement(By.xpath("//input[@name=\"inName\"]")).getAttribute("minlength");
        return  str;
    }

    // Desc field length
    public String getMaxLengthDesc() {
        String str = driver.findElement(description).getAttribute("maxlength");
        return  str;
    }
    public String getMinLengthDesc() {
        String str = driver.findElement(description).getAttribute("minlength");
        return  str;
    }
    // Name field
    public String getPlaceholderName() {
        String str = driver.findElement(By.xpath("//input[@name=\"inName\"]")).getAttribute("placeholder");
        return  str;
    }

    //description label
    By descriptionLabel = By.xpath("//h3[contains(text(),\"Description\")]");
    public WebElement getDescriptionLabel() {
        return driver.findElement(descriptionLabel);
    }

    //Description field
    By description = By.xpath("//div[@class=\"mat-form-field-infix\"]/textarea");
    public WebElement getDescription() {
        return driver.findElement(description);
    }

    //Label ofAdd members
    By addLabel = By.xpath("//h3[contains(text(),\"Add Members\")]");
    public WebElement getAddMembersLabel(){
        return driver.findElement(addLabel);
    }

    //Object role of the member
    By role = By.xpath("//select[@ng-reflect-form=\"[object Object]\"]");
    public WebElement getRole() {
        return driver.findElement(role);
    }

    //Search by username
    By username = By.xpath("//input[@placeholder=\"Search by username.\"]");
    public WebElement getUsername() {
        return driver.findElement(username);
    }

    //Add button
    By addButton = By.xpath("//div[@class=\"mat-form-field-infix\"]/div/button");
    public WebElement getAddButton(){
        return driver.findElement(addButton);
    }

    //Member List
    By memberList = By.xpath("//mat-accordion[@multi=\"true\"]");
    public WebElement getMemberList(){
        return driver.findElement(memberList);
    }
    //Member add message ("No members added yet.")
    By memberMessage = By.xpath("//*[@id=\"fulldialog\"]/form/div[1]/div/div[2]/p");
    public WebElement getMemberMessage(){
        return driver.findElement(memberMessage);
    }
    //button delete into list of members
    By deleteMember = By.xpath("//button[@id=\"deletebutton\"]");
    public WebElement getDeleteMember(){
        return driver.findElement(deleteMember);
    }
    ///////////////Date objects///////////////////////////////////////////////////////////////////

    //Label Start date
    By startDateLabel = By.xpath("//h4[contains(text(),\"Start Date\")]");
    public WebElement getStartDateLabel(){
        return driver.findElement(startDateLabel);
    }

    //Choose a date
    By startDateField = By.xpath("//input[@name=\"inDate1\"]");
    public WebElement getStartDate() {
        return driver.findElement(startDateField);
    }

    //Calendar button
    By startDateCalendar= By.xpath("//mat-datepicker-toggle[@class=\"mat-datepicker-toggle\"]/button[@tabindex=\"0\"]");
    public WebElement getCalendarIcon(){
        return driver.findElement(startDateCalendar);
    }

    // End Date button
    By endDateLabel = By.xpath("//span[contains(text(),\"End Date\")]");
    public WebElement getEndDate() {
        return driver.findElement(endDateLabel);
    }

    //Choose a date for end date
    By endDateField = By.xpath("//input[@name=\"inDate2\"]");
    public WebElement getEndDateField(){
        return driver.findElement(endDateField);
    }

    //Calendar button (end date)
    By endDateCalendar = By.xpath("//mat-form-field[@id=\"picker2\"]/div/div[1]/div[2]/mat-datepicker-toggle/button");
    public WebElement getEndDateCalendar(){
        return driver.findElement(endDateCalendar);
    }

    //Checkbox end date
    By checkBox = By.xpath("//mat-checkbox[@id=\"endbutt\"]");
    public WebElement getCheckBox() {
        return driver.findElement(checkBox);
    }

    //Create button
    By createButton = By.xpath("//button[@id=\"createbutt\"]");
    public WebElement getCreateButton() {
        return driver.findElement(createButton);
    }

    //Change photo button
    By photo = By.xpath("//div[@class=\"divchangepho\"]");
    public WebElement getPhoto() {
        return driver.findElement(photo);
    }

    //Image project
    By image = By.xpath("//img[@id=\"project-pic\"]");
    public WebElement getImage() {
        return driver.findElement(image);
    }

    //Label NEW PROJECT
    By Title = By.xpath("//h1[contains(text(),\"New Project\")]");
    public WebElement getPageTitle() {
        return driver.findElement(Title);
    }

    //Close Button ("X")
    By closeButton = By.xpath("//div[@id=\"fulldialog\"]/div/div/button");
    public WebElement getcloseButton() {
        return driver.findElement(closeButton);
    }

    //select date
    By selectDate = By.xpath("//*[@id=\"mat-datepicker-4\"]/div/mat-month-view/table/tbody/tr[5]/td[6]/div");
    public WebElement getSelectDate(){ return driver.findElement(selectDate); }

    //Change month
    By changeMonth = By.xpath("//*[@id=\"mat-datepicker-4\"]/mat-calendar-header/div/div/button[3]");
    public WebElement getChangeMonth(){ return driver.findElement(changeMonth); }

    //select date
    By selectActiveDate = By.xpath("//*[@class=\"mat-calendar-body-cell mat-calendar-body-active ng-star-inserted\"]");
    public WebElement getSelectActiveDate(){ return driver.findElement(selectActiveDate); }

    By changeDayStart = By.xpath("//td[@class=\"mat-calendar-body-cell mat-calendar-body-active ng-star-inserted\"]");
    public WebElement getChangeDay(){ return driver.findElement(changeDayStart); }

    By changeDayEnd = By.xpath("//td[@class=\"mat-calendar-body-cell mat-calendar-body-active ng-star-inserted\"]");
    public WebElement getChangeDayE(){ return driver.findElement(changeDayEnd); }


    /////////////////////////////////////////////////////Error Messages////////////////////////////////////////////////////////////////

    By nameError = By.xpath("//mat-error[contains(text(),\"You need to enter a name.\")]");
    public WebElement getnameError() {
        return driver.findElement(nameError);
    }

    By descError = By.xpath("//mat-error[contains(text(),\"You need to enter a description.\")]");
    public WebElement getdescError() {
        return driver.findElement(descError);
    }

    By dateError = By.xpath("//mat-error[contains(text(),\"You need to enter an initial date.\")]");
    public WebElement getdateError() {
        return driver.findElement(dateError);
    }

    By createProj = By.xpath("//h1/a[contains(text(),\"new project\")]");
    public WebElement getInitialCreateProject() {
        return driver.findElement(createProj);
    }

}

