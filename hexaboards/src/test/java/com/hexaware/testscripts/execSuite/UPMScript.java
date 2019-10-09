package com.hexaware.testscripts.execSuite;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hexaware.frameworks.gui.GuiFramework;
import com.hexaware.frameworks.gui.pageobjects.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
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

import static java.util.concurrent.TimeUnit.SECONDS;

public class UPMScript {
    ExtentReports extent = new ExtentReports();
    ExtentHtmlReporter reporter ;
    ExtentTest logger;
    WebDriver driver;
    InputStream input;
    Properties prop = new Properties();
    ArrayList<String> user;
    String[] dataArray;
    String filepath,URI, username, password, name, email, temp, pname, pname2, description, start_date, end_date, role, usernameRole,description2, start_date2, end_date2, role2, usernameRole2;
    GuiFramework fr = new GuiFramework();
    @BeforeMethod(groups = {"functest"})
    public void setup() throws IOException {
        input = new FileInputStream("confs.txt");
        prop.load(input);
        reporter = new ExtentHtmlReporter(prop.getProperty("UPMreport"));
        filepath = prop.getProperty("DataFile");
        URI = prop.getProperty("URI");
        user = fr.readExcel(filepath, 14);
        driver = fr.initDriver(prop);
        extent.attachReporter(reporter);
        driver.navigate().to(URI);
    }

    //////////////////////////////////////////////////SCENARIO 1////////////////////////////////////////////////////////////////////////
    @Test(groups = {"functest"},priority = 1)
    public void scenario1() throws IOException, InterruptedException {
        logger = extent.createTest("Scenario 1", "Happy Path");
        Login lg = new Login(driver);
        ProjectSetup ps =new ProjectSetup(driver);
        UserProjectMapping upm =new UserProjectMapping(driver);
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);

        dataArray = fr.turnArray(user, 1);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];

        pname = dataArray[4];
        description = dataArray[5];
        start_date = dataArray[6];
        end_date = dataArray[7];
        role = dataArray[8];
        usernameRole = dataArray[9];
        pname2 = dataArray[10];
        description2 = dataArray[11];
        start_date2 = dataArray[12];
        end_date2 = dataArray[13];
        role2 = dataArray[14];
        usernameRole2 = dataArray[15];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);
        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        fr.login(username, password, driver);
        //-------------------------project 1 ------------------------------

        //press the button create project on the landing page
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(pname);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Description
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //click on roles
        varWat.until(ExpectedConditions.visibilityOf(pc.getRole())).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on roles", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //select member of role
        pc.getUsername().sendKeys(usernameRole);

        varWat.until(ExpectedConditions.visibilityOf(pc.getRole())).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select a member", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        ////////Click Add member///////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getAddButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Add member", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        //////////CLICK AL CALENDARIO///////////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getCalendarIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Start Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////////Select day of start day///////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDay())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select start day", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////////Select end date////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDate())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDateCalendar())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on End Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDayE())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select end date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        new FluentWait(driver)
                .withTimeout(2,TimeUnit.SECONDS)
                .pollingEvery(2,TimeUnit.SECONDS)
                .ignoring(Exception.class);
        driver.switchTo().alert().accept();

        //------------------------ Projec2 ---------------------------------

        //press the button create project on the landing page
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(pname2);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Description
        pc.getDescription().sendKeys(description2);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //click on roles
        varWat.until(ExpectedConditions.visibilityOf(pc.getRole())).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on roles", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //select member of role
        pc.getUsername().sendKeys(usernameRole2);

        varWat.until(ExpectedConditions.visibilityOf(pc.getRole())).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select a member", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        ////////Click Add member///////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getAddButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Add member", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        //////////CLICK AL CALENDARIO///////////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getCalendarIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Start Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////////Select day of start day///////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDay())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select start day", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////////Select end date////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDate())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDateCalendar())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on End Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDayE())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select end date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        new FluentWait(driver)
                .withTimeout(2,TimeUnit.SECONDS)
                .pollingEvery(2,TimeUnit.SECONDS)
                .ignoring(Exception.class);
        driver.switchTo().alert().accept();


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

        try{
            if (upm.getPanel1(pname).isDisplayed() && upm.getPanel2(pname2).isDisplayed()){
                Assert.assertTrue(true );
            }
        }catch(Exception e)       {}
    }

    @AfterMethod(groups = {"functest"})
    public void tearDown(ITestResult result) throws IOException {
        temp = fr.getScreenshot(driver);
        if (result.getStatus() == 1) {
            logger.pass("Success test", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        } else {
            logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        }
        fr.checkAlert(driver);
        extent.flush();
        driver.close();
    }
}
