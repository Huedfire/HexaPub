package com.hexaware.testscripts.execSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hexaware.frameworks.gui.GuiFramework;
import com.hexaware.frameworks.gui.pageobjects.StoriesAdmin;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class StoriesAdm {
    ExtentReports extent = new ExtentReports();
    ExtentHtmlReporter reporter;
    ExtentTest logger;
    WebDriver driver;
    InputStream input;
    Properties prop = new Properties();
    String filepath, URI, username, password, temp, story, story2,sprint;
    ArrayList<String> user, stories;
    String[] dataArray;
    GuiFramework fr = new GuiFramework();
    StoriesAdmin sa;

    @BeforeMethod(groups = {"functest"})
    public void setup() throws IOException {
        input = new FileInputStream("confs.txt");
        prop.load(input);
        reporter = new ExtentHtmlReporter(prop.getProperty("StAdmreport"));
        filepath = prop.getProperty("DataFile");
        URI = prop.getProperty("URI");
        user = fr.readExcel(filepath, 0);
        stories = fr.readExcel(filepath,10);
        driver = fr.initDriver(prop);
        extent.attachReporter(reporter);
        driver.navigate().to(URI);
        sa = new StoriesAdmin(driver);
    }

    @Test(groups = {"functest"})
    public void TS_STA() throws IOException, InterruptedException {
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        //login
        logger = extent.createTest("Scenario 1", "Loggin to the app");
        dataArray = fr.turnArray(user, 2);
        username = dataArray[0];
        password = dataArray[1];
        fr.login(username, password, driver);

        //click on burguer button
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.visibilityOf(sa.getOptionIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on burguer button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //click on myprojects button
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.visibilityOf(sa.getMyProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on \"My projects\" button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //click on expansion panel
        Thread.sleep(1000);
        varWat.until(ExpectedConditions.visibilityOf(sa.getExpansionPanel())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //click on "Open" button
         driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
         varWat.until(ExpectedConditions.visibilityOf(sa.getOpenButton())).click();
         temp = fr.getScreenshot(driver);
         logger.pass("Click on 'Open' button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Read data file
        dataArray = fr.turnArray(stories, 1);
        story = dataArray[0];
        sprint = dataArray[1];
        System.out.println(sprint);
         //click on sprint
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // varWat.until(ExpectedConditions.visibilityOf(sa.getSprint2())).click();
        sa.getPanel(sprint).click();
         temp = fr.getScreenshot(driver);
         logger.pass("Click on the sprint", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

         //Click on "go to" button
         driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        // varWat.until(ExpectedConditions.visibilityOf(sa.getGoToBtn())).click();
        sa.getGoToBtn3().click();
         temp = fr.getScreenshot(driver);
         logger.pass("Click on 'Go to Board' button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Click on Add story
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.visibilityOf(sa.getAddStoryButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on \"Add Story\" button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        Assert.assertTrue(sa.getCreateStoryBtn().isDisplayed());

        //Read data file
        //dataArray = fr.turnArray(stories, 1);
       // story = dataArray[0];

        //Enter a story
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.visibilityOf(sa.getEnterStory())).sendKeys(story);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a description of the story", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Click on "CreateStory" button
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.visibilityOf(sa.getCreateStoryBtn())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on 'Create story' button",MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        Thread.sleep(2000);
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Story created"),"Does not display a message. //");
        temp = fr.getScreenshot(driver);
        //fr.checkAlert(driver);
        driver.switchTo().alert().accept();
        logger.pass("Alert 'Story created'" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        ////////////////////////////////////////Edit Story//////////////////////////////////////////////////////////////////

        //edit Story
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(sa.getEditStory(story))).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on story", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Assert.assertTrue(pi.getVerifiedName(name).isDisplayed() && pi.getVerifiedEmail(email).isDisplayed());
        Assert.assertTrue(sa.getLabelEditStory().isDisplayed());

        //Read data file & edit story
        dataArray = fr.turnArray(stories, 2);
        story2 = dataArray[0];
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.visibilityOf(sa.getEditTextDiv())).sendKeys(Keys.chord(Keys.CONTROL,"a"),"");
        varWat.until(ExpectedConditions.visibilityOf(sa.getEditTextDiv())).sendKeys(story2);
        temp = fr.getScreenshot(driver);
        logger.pass("Edit the description", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Updated succesfully

        Thread.sleep(2000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(sa.getEditStoryBtn())).click();
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Updated succesfully"),"Does not display a message. //");
        temp = fr.getScreenshot(driver);
        driver.switchTo().alert().accept();
        logger.pass("Alert 'Updated succesfully'" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());


        //Click on Add story
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.visibilityOf(sa.getAddStoryButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on \"Add Story\" button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        Assert.assertTrue(sa.getCreateStoryBtn().isDisplayed());

        //Read data file
        dataArray = fr.turnArray(stories, 3);
        story = dataArray[0];
        sprint = dataArray[1];

        //Enter a story
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.visibilityOf(sa.getEnterStory())).sendKeys(story);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a description of the story", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Click on "CreateStory" button
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.visibilityOf(sa.getCreateStoryBtn())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on 'Create story' button",MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        Thread.sleep(2000);
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Story created"),"Does not display a message. //");
        temp = fr.getScreenshot(driver);
        //fr.checkAlert(driver);
        driver.switchTo().alert().accept();
        logger.pass("Alert 'Story created'" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());



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
