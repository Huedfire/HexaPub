package com.hexaware.testscripts.execSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hexaware.frameworks.gui.GuiFramework;
import com.hexaware.frameworks.gui.pageobjects.BoardPage2;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
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
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class BoardPagePet {
    ExtentReports extent = new ExtentReports();
    ExtentHtmlReporter reporter;
    ExtentTest logger;
    WebDriver driver;
    WebElement element;
    InputStream input;
    Properties prop = new Properties();
    String filepath, URI, username, password, temp,panels ="Sprint1";
    ArrayList<String> user;
    String[] dataArray;
    GuiFramework fr = new GuiFramework();
    BoardPage2 bp;
    Actions builder;

    @BeforeMethod(groups = {"functest"})
    public void setup() throws IOException {
        input = new FileInputStream("confs.txt");
        prop.load(input);
        reporter = new ExtentHtmlReporter(prop.getProperty("BPMreport"));
        filepath = prop.getProperty("DataFile");
        URI = prop.getProperty("URI");
        user = fr.readExcel(filepath, 0);
        driver = fr.initDriver(prop);
        extent.attachReporter(reporter);
        driver.navigate().to(URI);
        bp = new BoardPage2(driver);
        builder = new Actions(driver);
    }

    @Test(groups = {"functest"})
    public void TS_BPM() throws IOException, InterruptedException {
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        //login
        logger = extent.createTest("Scenario 1", "Loggin to the app");
        dataArray = fr.turnArray(user, 1);
        username = dataArray[0];
        password = dataArray[1];
        fr.login(username, password, driver);

        //click on burguer button
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.visibilityOf(bp.getOptionIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on burguer button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //click on myprojects button
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.visibilityOf(bp.getMyProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on \"My projects\" button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        bp.getSettingsIcon().click();
        //click on expansion panel
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(bp.getExpansionPanel())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //click on "Open" button
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
       varWat.until(ExpectedConditions.visibilityOf(bp.getOpenButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on 'Open' button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //click on sprint
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      //  varWat.until(ExpectedConditions.visibilityOf(bp.getPanel(panels))).click();
      bp.getPanel(panels).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on the first sprint", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Click on "go to" button
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      //  varWat.until(ExpectedConditions.visibilityOf(bp.getGoToBtn())).click();
        bp.getGoToBtn3().click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on 'Go to Board' button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        /////////////////////////////////////drag and drop////////////////////////////////////////////////////////////////////
        //Backlog a To Do
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class);
        element= wait.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getToDo();
            }
        });
        Action dragAndDrop = builder.clickAndHold(bp.getFirstBacklog())
                .release(bp.getToDo())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'Backlog' to 'To Do'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstToDo().isEnabled());
        Thread.sleep(2000);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //To do to In progress
        //Fluent wait for test
        Wait<WebDriver> wait1 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class);
        // wait1.until(webDriver -> bp.getToDo());
        element= wait1.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return bp.getToDo();
            }
        });
        dragAndDrop = builder.clickAndHold(bp.getFirstToDo())
                .release(bp.getInProgDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'To do' to 'In progress'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstInProg().isEnabled());
        Thread.sleep(2000);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //In progress to test
        //Fluent wait for test
        Wait<WebDriver> wait2 = new FluentWait<>(driver)
                .withTimeout(40, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class);
        // wait2.until(webDriver -> bp.getTestDiv());

        element = wait2.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getTestDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstInProg())
                .release(bp.getTestDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'In progress' to 'Test'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstTest().isEnabled());
        Thread.sleep(2000);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //test to review
        Wait<WebDriver> wait3 = new FluentWait<>(driver)
                .withTimeout(40, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        //wait3.until(webDriver -> bp.getReviewDiv());

        element = wait3.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getReviewDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstTest())
                .release(bp.getReviewDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'Test to Review", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstReview().isEnabled());
        Thread.sleep(2000);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////Review to test////////////
        Wait<WebDriver> wait4 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class);
        //  wait4.until(webDriver -> bp.getTestDiv());
        element = wait4.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getTestDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstReview())
                .release(bp.getTestDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'Review' to 'Test'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstTest().isEnabled());
        Thread.sleep(2000);
        /////////////////////////////////////////////////////////////////////////////////////////////
        //test to in progress
        Wait<WebDriver> wait5 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class);
        //wait5.until(webDriver -> bp.getInProgDiv());
        element = wait5.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getInProgDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstTest())
                .release(bp.getInProgDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'Test' to 'In progress'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstInProg().isEnabled());
        Thread.sleep(3000);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////
        //In progress to Review
        Wait<WebDriver> wait6 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        //wait6.until(webDriver -> bp.getReviewDiv());
        element = wait6.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getReviewDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstInProg())
                .moveToElement(bp.getReviewDiv())
                .release(bp.getReviewDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'In progress' to 'Review'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstReview().isEnabled());
        Thread.sleep(3000);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //review to in progress
        Wait<WebDriver> wait7 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        //wait7.until(webDriver -> bp.getFirstReview());
        element = wait7.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getFirstReview();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstReview())
                .moveToElement(bp.getInProgDiv())
                .release(bp.getInProgDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'Review' to 'In progress'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstInProg().isEnabled());
        Thread.sleep(2000);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        //In progress to done
        Wait<WebDriver> wait8 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        //wait8.until(webDriver -> bp.getDoneDiv());
        element = wait8.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getDoneDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstInProg())
                .release(bp.getDoneDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'In progress' to 'Done'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstDone().isEnabled());
        Thread.sleep(2000);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //backlog to in progress
        Wait<WebDriver> wait9 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        //  wait9.until(webDriver -> bp.getInProgDiv());

        element = wait9.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getInProgDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstBacklog())
                .release(bp.getInProgDiv())
                .build();
        dragAndDrop.perform();

        temp = fr.getScreenshot(driver);
        logger.pass("'Backlog' to 'In progress'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstInProg().isEnabled());
        Thread.sleep(2000);

////////////////////////////////////////////////////////////////////////////////////////////////////
        //backlog to test
        Wait<WebDriver> wait10 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        // wait10.until(webDriver -> bp.getTestDiv());
        element = wait10.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getTestDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstBacklog())
                .release(bp.getTestDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'Backlog' to 'Test'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstTest().isEnabled());
        Thread.sleep(2000);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Test to done
        Wait<WebDriver> wait11 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        //wait11.until(webDriver -> bp.getDoneDiv());

        element = wait11.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getDoneDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstTest())
                .release(bp.getDoneDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'Test' to 'Done'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstDone().isEnabled());
        Thread.sleep(2000);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //backlog to review
        Wait<WebDriver> wait12 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        //  wait12.until(webDriver -> bp.getDoneDiv());

        element = wait12.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getDoneDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstBacklog())
                .release(bp.getReviewDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'Backlog' to 'Review'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstReview().isEnabled());
        Thread.sleep(2000);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //review to done
        Wait<WebDriver> wait13 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        // wait13.until(webDriver -> bp.getDoneDiv());
        element = wait13.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getDoneDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstReview())
                .release(bp.getDoneDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'Review' to 'Done'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstDone().isEnabled());
        Thread.sleep(2000);
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Backlog to done
        Wait<WebDriver> wait14 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        // wait14.until(webDriver -> bp.getDoneDiv());
        element = wait14.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getDoneDiv();
            }});

        dragAndDrop = builder.clickAndHold(bp.getFirstBacklog())
                .release(bp.getDoneDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'Backlog' to 'Done'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstDone().isEnabled());
        Thread.sleep(2000);
        /////////////////////////////////////////////////////////////////////////////////////////////////
        //To Do to done
        Wait<WebDriver> wait15 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        //wait15.until(webDriver -> bp.getDoneDiv());
        element = wait15.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getDoneDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstToDo())
                .release(bp.getDoneDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'To do' to 'Done'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstDone().isEnabled());
        Thread.sleep(2000);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //to do to test
        Wait<WebDriver> wait17 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        //wait17.until(webDriver -> bp.getDoneDiv());
        element = wait17.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getDoneDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstToDo())
                .release(bp.getTestDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'To do' to 'Test'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstTest().isEnabled());
        Thread.sleep(2000);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //to do to review
        Wait<WebDriver> wait18 = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(10, TimeUnit.SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
        // wait18.until(webDriver -> bp.getReviewDiv());
        element = wait18.until(new Function<WebDriver, WebElement>() {

            public WebElement apply(WebDriver driver) {
                return bp.getReviewDiv();
            }
        });

        dragAndDrop = builder.clickAndHold(bp.getFirstToDo())
                .release(bp.getReviewDiv())
                .build();
        dragAndDrop.perform();
        temp = fr.getScreenshot(driver);
        logger.pass("'To do' to 'Review'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Assert.assertTrue(bp.getFirstReview().isEnabled());
    }

    @AfterMethod(groups = {"functest"})
    public void tearDown (ITestResult result) throws IOException {
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
