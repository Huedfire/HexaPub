package com.hexaware.testscripts.execSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hexaware.frameworks.gui.GuiFramework;
import com.hexaware.frameworks.gui.pageobjects.ProjectCreation;
import com.hexaware.frameworks.gui.pageobjects.ProjectSetup;
import org.openqa.selenium.JavascriptExecutor;
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


public class ProjectSetupGUI {

    ExtentReports extent = new ExtentReports();
    ExtentTest logger;
    ExtentHtmlReporter reporter ;
    WebDriver driver;
    InputStream input;
    Properties prop = new Properties();
    WebElement element;
    //Login

    String usernameLo,passwordLo,filepath,URI,name,description,temp,script;
    //Project setup
    ArrayList<String> projsetup;
    String[] dataArray;
    //Sprint
    ArrayList<String> nameSprint;
    String[] dataArrayS;
    GuiFramework fr = new GuiFramework();
    JavascriptExecutor je = null;
    // This code will run before executing any testcase
    @BeforeMethod(groups = {"functest"})
    public void setup() throws IOException {
        input = new FileInputStream("confs.txt");
        prop.load(input);
        filepath = prop.getProperty("DataFile");
        URI = prop.getProperty("URI");
        reporter = new ExtentHtmlReporter(prop.getProperty("PSreport"));
        nameSprint = fr.readExcel(filepath, 12);
        projsetup = fr.readExcel(filepath, 11);
        driver = fr.initDriver(prop);
        extent.attachReporter(reporter);
        driver.navigate().to(URI);
    }

    @Test(groups = {"functest"}, priority = 1)
    public void scenario1() throws IOException, InterruptedException {
        logger = extent.createTest("Project Setup Scenario 1", "A short description");

        dataArray = fr.turnArray(projsetup, 1);
        description = dataArray[0];
        usernameLo = dataArray[1];
        passwordLo = dataArray[2];


        ProjectSetup ps =new ProjectSetup(driver);
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
        ps.getProject().sendKeys(Keys.ESCAPE);

        //Project Setup Objects
        ps.getEditDescriptionTextArea().sendKeys(Keys.chord(Keys.CONTROL, "a"), "");
        ps.getEditDescriptionTextArea().sendKeys(Keys.BACK_SPACE, description);
        temp = fr.getScreenshot(driver);
        logger.pass("Type on description text area", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(ps.getEditDescriptionButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Edit description button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Assert
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Description updated succesfully"),"Does not display a message.  //");

        //check alert
        driver.switchTo().alert().accept();
        //Refresh page
        driver.navigate().refresh();

    }

    @Test(groups = {"functest"}, priority = 8)
    public void scenario8() throws IOException, InterruptedException {
        logger = extent.createTest("Project Setup Scenario 8", "A new sprint");

        dataArrayS = fr.turnArray(nameSprint, 2);
        name = dataArrayS[0];
        usernameLo = dataArrayS[1];
        passwordLo = dataArrayS[2];


        ProjectSetup ps =new ProjectSetup(driver);
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
        ps.getProject().sendKeys(Keys.ESCAPE);

        //Project Setup Objects
        varWat.until(ExpectedConditions.elementToBeClickable(ps.getAddSprintButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Add sprint button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(ps.getSprintNameTextArea())).sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Sprint name text area", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        varWat.until(ExpectedConditions.elementToBeClickable(ps.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Assert
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Sprint created."),"Does not display a message.  //");
        //check alert
        driver.switchTo().alert().accept();
        //Refresh page
        driver.navigate().refresh();

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



