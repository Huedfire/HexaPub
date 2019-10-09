package com.hexaware.testscripts.execSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hexaware.frameworks.gui.GuiFramework;
import com.hexaware.frameworks.gui.pageobjects.ProjectCreation;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
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

public class ProjectCreationScript {
    ExtentReports extent = new ExtentReports();
    ExtentHtmlReporter reporter ;
    ExtentTest logger;
    WebDriver driver;
    InputStream input;
    Properties prop = new Properties();
    ArrayList<String> user;
    String[] dataArray;
    String username, password, name, temp,  description, start_date, end_date, role, usernameRole,filepath,URI;
    GuiFramework fr = new GuiFramework();

    @BeforeMethod(groups = {"functest"})
    public void setup() throws IOException {
        input = new FileInputStream("confs.txt");
        prop.load(input);
        reporter = new ExtentHtmlReporter(prop.getProperty("PCreport"));
        filepath = prop.getProperty("DataFile");
        URI = prop.getProperty("URI");
        user = fr.readExcel(filepath, 6);
        driver = fr.initDriver(prop);
        extent.attachReporter(reporter);
        driver.navigate().to(URI);
    }


    //////////////////////////////////////////////////SCENARIO 2////////////////////////////////////////////////////////////////////////
    @Test(groups = {"functest"},priority = 2)
    public void scenario2() throws IOException, InterruptedException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario", "Send a request with a correct information");
        dataArray = fr.turnArray(user, 2);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        //press the button create project on the landing page
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
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

        Assert.assertTrue(driver.switchTo().alert().getText().contains("Project created"));
        driver.switchTo().alert().accept();
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



