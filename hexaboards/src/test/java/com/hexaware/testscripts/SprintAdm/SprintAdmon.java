package com.hexaware.testscripts.SprintAdm;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hexaware.frameworks.gui.GuiFramework;
import com.hexaware.frameworks.gui.pageobjects.BoardPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
public class SprintAdmon {
    ExtentReports extent = new ExtentReports();
    ExtentHtmlReporter reporter ;
    ExtentTest logger;
    WebDriver driver;
    WebElement element;
    InputStream input;
    Properties prop = new Properties();
    String filepath, URI, username, password, temp,sprint1,sprint2;
    ArrayList<String> user;
    String[] dataArray;
    GuiFramework fr = new GuiFramework();

    @BeforeMethod(groups = {"functest"})
    public void setup() throws IOException {
        input = new FileInputStream("confs.txt");
        prop.load(input);
        reporter = new ExtentHtmlReporter(prop.getProperty("SPAdmon"));
        filepath = prop.getProperty("DataFile");
        URI = prop.getProperty("URI");
        user = fr.readExcel(filepath, 9);
        driver = fr.initDriver(prop);
        extent.attachReporter(reporter);
        driver.navigate().to(URI);
    }
    @Test
    public void TS_SprintAdmon() throws IOException, InterruptedException {
        BoardPage bp = new BoardPage(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        //login
        logger = extent.createTest("Scenario 1", "Loggin to the app");
        dataArray = fr.turnArray(user, 1);
        username = dataArray[0];
        password = dataArray[1];
        sprint1 = dataArray[2];
        sprint2 = dataArray[3];
        fr.login(username, password, driver);



        //click on burguer button
        varWat.until(ExpectedConditions.visibilityOf(bp.getOptionIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on burguer button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //click on myprojects button
        varWat.until(ExpectedConditions.elementToBeClickable(bp.getMyProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on \"My projects\" button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getExpansionPanel().isDisplayed());

        //click on expansion panel
        varWat.until(ExpectedConditions.visibilityOf(bp.getExpansionPanel())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getOpenButton().isDisplayed());

        //click on "Open" button
        varWat.until(ExpectedConditions.visibilityOf(bp.getOpenButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on 'Open' button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //click on sprint
      //  varWat.until(ExpectedConditions.visibilityOf(bp.getSprint1())).click();
        bp.getPanel(sprint1).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on the first sprint", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //Click on "go to board" button
       // varWat.until(ExpectedConditions.elementToBeClickable(bp.getGoToBtn())).click();
        bp.getGoToBtn3().click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on 'Go to Board' button",MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getBacklog().isDisplayed());

        /////////////////////////////////////drag and drop////////////////////////////////////////////////////////////////////

       //BANK drag and drop.
        Actions act = new Actions(driver);
        //element wich need to drag
        act.dragAndDrop(bp.getFirstBacklog(), bp.getDoneField()).build().perform();
        temp = fr.getScreenshot(driver);
        logger.pass("Drag & drop", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //////////Second element///////
        act.dragAndDrop(bp.getFirstBacklog(), bp.getInProgDiv()).build().perform();
        temp = fr.getScreenshot(driver);
        logger.pass("Drag & drop", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        ///////////End Sprint///////////////////
        varWat.until(ExpectedConditions.elementToBeClickable(bp.getEndSprint())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on End Sprint", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        fr.checkAlert(driver);
        Assert.assertTrue(bp.getDone().isDisplayed());
        ////////back to see the sprints///////////////////////////////
        driver.navigate().back();
        /////////Check the backlog of the next sprint
        //click on sprint 2
       // varWat.until(ExpectedConditions.visibilityOf(bp.getSprint2())).click();
        bp.getPanel(sprint2).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on the second sprint", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //Click on "go to board" button sprint 2
       // varWat.until(ExpectedConditions.elementToBeClickable(bp.getGoToBtn2())).click();
        bp.getGoToBtn3().click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on 'Go to Board' button",MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getBacklog().isDisplayed());

    }
    @AfterMethod
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
