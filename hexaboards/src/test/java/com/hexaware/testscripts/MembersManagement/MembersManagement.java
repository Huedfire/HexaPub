package com.hexaware.testscripts.MembersManagement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hexaware.frameworks.gui.GuiFramework;
import com.hexaware.frameworks.gui.pageobjects.ProjectCreation;
import com.hexaware.frameworks.gui.pageobjects.ProjectSetup;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MembersManagement {

    ExtentReports extent = new ExtentReports();
    ExtentTest logger;
    ExtentHtmlReporter reporter ;
    WebDriver driver;
    InputStream input;
    Properties prop = new Properties();
    WebElement element;
    //Login
    String[] dataArrayL;
    ArrayList<String> usernameL;
    String usernameLo,passwordLo,user,filepath,URI,temp;
    //Members management
    ArrayList<String> members;
    String [] dataArray;
    GuiFramework fr = new GuiFramework();
    JavascriptExecutor je = null;


    // This code will run before executing any testcase
    @BeforeMethod(groups = {"functest"})
    public void setup() throws IOException {
        input = new FileInputStream("confs.txt");
        prop.load(input);
        filepath = prop.getProperty("DataFile");
        URI = prop.getProperty("URI");
        reporter = new ExtentHtmlReporter(prop.getProperty("MMreport"));
        members = fr.readExcel(filepath, 13);
        driver = fr.initDriver(prop);
        extent.attachReporter(reporter);
        driver.navigate().to(URI);
    }

    @Test(groups = {"functest"}, priority = 1)
    public void scenario1() throws IOException, InterruptedException {
        logger = extent.createTest("Members management Scenario 1", "Add a new member as “Scrum Master");

        dataArray = fr.turnArray(members, 1);
        user = dataArray[0];
        usernameLo = dataArray[1];
        passwordLo = dataArray[2];


        ProjectSetup ps = new ProjectSetup(driver);
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);

        fr.login(usernameLo, passwordLo, driver);

        //Project objects
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getOptionIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Menu Options Icon", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(pc.getMyProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on My projects", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Project Objects
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(ps.getBody())).click();

        element = ps.getProject();
        varWat.until(ExpectedConditions.visibilityOf(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(ps.getOpenButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Open button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(ps.getAddMemberButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Add member button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.visibilityOf(ps.getEditRoleList())).sendKeys(Keys.ARROW_DOWN,  Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        temp = fr.getScreenshot(driver);
        logger.pass("Edit role list", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.visibilityOf(ps.getFindUser())).sendKeys(user, Keys.ESCAPE);
        temp = fr.getScreenshot(driver);
        logger.pass("Find a user", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(ps.getAddButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on add button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        Assert.assertTrue(driver.switchTo().alert().getText().contains("Member added succesfully."),"Does not display a message.  //");

        //check alert
        driver.switchTo().alert().accept();
        //Refresh page
        driver.navigate().refresh();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", ps.getExpansionPanelMembers() );


    }

    @Test(groups = {"functest"}, priority = 2)
    public void scenario2() throws IOException, InterruptedException {
        logger = extent.createTest("Members management Scenario 2", "Add a new member as “Team member");

        dataArray = fr.turnArray(members, 2);
        user = dataArray[0];
        usernameLo = dataArray[1];
        passwordLo = dataArray[2];


        ProjectSetup ps = new ProjectSetup(driver);
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);

        fr.login(usernameLo, passwordLo, driver);

        //Project objects
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getOptionIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Menu Options Icon", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(pc.getMyProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on My projects", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Project Objects
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(ps.getBody())).click();

        element = ps.getProject();
        varWat.until(ExpectedConditions.visibilityOf(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(ps.getOpenButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Open button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(ps.getAddMemberButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Add member button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.visibilityOf(ps.getEditRoleList())).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        temp = fr.getScreenshot(driver);
        logger.pass("Edit role list", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.visibilityOf(ps.getFindUser())).sendKeys(user, Keys.ESCAPE);
        temp = fr.getScreenshot(driver);
        logger.pass("Find a user", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(ps.getAddButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on add button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());


        //Assert
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Member added succesfully."),"Does not display a message.  //");

        //check alert
        driver.switchTo().alert().accept();
        //Refresh page
        driver.navigate().refresh();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", ps.getExpansionPanelMembers() );

    }

    @Test(groups = {"functest"}, priority = 3)
    public void scenario3() throws IOException, InterruptedException {
        logger = extent.createTest("Members management Scenario 3", "Add a new member as “Product owner");

        dataArray = fr.turnArray(members, 3);
        user = dataArray[0];
        usernameLo = dataArray[1];
        passwordLo = dataArray[2];


        ProjectSetup ps = new ProjectSetup(driver);
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);

        fr.login(usernameLo, passwordLo, driver);

        //Project objects
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getOptionIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Menu Options Icon", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(pc.getMyProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on My projects", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Project Objects
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(ps.getBody())).click();

        element = ps.getProject();
        varWat.until(ExpectedConditions.visibilityOf(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(ps.getOpenButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Open button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(ps.getAddMemberButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Add member button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.visibilityOf(ps.getEditRoleList())).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        temp = fr.getScreenshot(driver);
        logger.pass("Edit role list", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.visibilityOf(ps.getFindUser())).sendKeys(user, Keys.ESCAPE);
        temp = fr.getScreenshot(driver);
        logger.pass("Find a user", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(ps.getAddButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on add button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Assert
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Member added succesfully."),"Does not display a message.  //");

        //check alert
        driver.switchTo().alert().accept();
        //Refresh page
        driver.navigate().refresh();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", ps.getExpansionPanelMembers() );


    }


    @Test(groups = {"functest"}, priority = 4)
    public void scenario4() throws IOException, InterruptedException {
        logger = extent.createTest("Members management Scenario 4", "Edit a member as “Team menber”");

        dataArray = fr.turnArray(members, 4);
        user = dataArray[0];
        usernameLo = dataArray[1];
        passwordLo = dataArray[2];


        ProjectSetup ps = new ProjectSetup(driver);
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);

        fr.login(usernameLo, passwordLo, driver);

        //My projects objects
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getOptionIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Menu Options Icon", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(pc.getMyProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on My projects", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(ps.getBody())).click();

        //Project objects
        varWat.until(ExpectedConditions.visibilityOf(ps.getProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Open project
        varWat.until(ExpectedConditions.elementToBeClickable(ps.getOpenButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Open button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());


        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", ps.getExpansionPanelMembers() );

        //expansion member list
        varWat.until(ExpectedConditions.visibilityOf(ps.getExpansionPanelMembers())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Expansion Panel member", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Click on edit rol
        varWat.until(ExpectedConditions.elementToBeClickable(ps.getEditRoleButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Edit role button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.visibilityOf(ps.getRoleList())).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        temp = fr.getScreenshot(driver);
        logger.pass("Edit role list", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(ps.getSaveButtonEditRole())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Save button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Assert
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Role updated succesfully"),"Does not display a message.  //");

        //check alert
        driver.switchTo().alert().accept();
        //Refresh page
        driver.navigate().refresh();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        JavascriptExecutor js4 = (JavascriptExecutor)driver;
        js4.executeScript("arguments[0].scrollIntoView();", ps.getExpansionPanelMembers() );

    }

    @Test(groups = {"functest"}, priority = 5)
    public void scenario5() throws IOException, InterruptedException {
        logger = extent.createTest("Members management Scenario 5", "Delete a member");

        dataArray = fr.turnArray(members, 4);
        user = dataArray[0];
        usernameLo = dataArray[1];
        passwordLo = dataArray[2];


        ProjectSetup ps = new ProjectSetup(driver);
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);

        fr.login(usernameLo, passwordLo, driver);

        //My projects objects
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getOptionIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Menu Options Icon", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(pc.getMyProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on My projects", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(ps.getBody())).click();

        //Project objects
        varWat.until(ExpectedConditions.visibilityOf(ps.getProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Open project
        varWat.until(ExpectedConditions.elementToBeClickable(ps.getOpenButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Open button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //scroll
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", ps.getExpansionPanelMembers() );

        //expansion member list
        varWat.until(ExpectedConditions.visibilityOf(ps.getExpansionPanelMembers())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Expansion Panel member", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Click on delete member
        varWat.until(ExpectedConditions.elementToBeClickable(ps.getDeleteButtonMember())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Delete member button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Assert
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Member deleted"),"Does not display a message.  //");

        //check alert
        driver.switchTo().alert().accept();
        //Refresh page
        driver.navigate().refresh();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        JavascriptExecutor js4 = (JavascriptExecutor)driver;
        js4.executeScript("arguments[0].scrollIntoView();", ps.getExpansionPanelMembers() );

    }






    @AfterMethod(groups = {"functest"})
    public void tearDown(ITestResult result) throws IOException {
        temp = fr.getScreenshot(driver);
        if (result.getStatus() == 1) {
            logger.pass("Success test", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        } else {
            logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        }
        extent.flush();
        driver.close();
    }


}
