package com.hexaware.testscripts.execSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hexaware.frameworks.gui.GuiFramework;
import com.hexaware.frameworks.gui.pageobjects.EditProfile;
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

public class EditProfileScript {
    ExtentReports extent = new ExtentReports();
    ExtentHtmlReporter reporter;
    ExtentTest logger;
    WebDriver driver;
    InputStream input;
    Properties prop = new Properties();
    WebElement element;
    //login
    ArrayList<String> usernameL;
    String usernameLo, passwordLo, filepath, URI, name, email,password, temp;
    boolean   verifiedEmail;
    //Edit profile
    ArrayList<String> nameEP;
    ArrayList<String> emailEP;
    ArrayList<String> passwordEP;
    String[] dataArray;

    GuiFramework fr = new GuiFramework();
    JavascriptExecutor je = null;


    // This code will run before executing any testcase
    @BeforeMethod(groups = {"functest"})
    public void setup() throws IOException {
        input = new FileInputStream("confs.txt");
        prop.load(input);
        reporter = new ExtentHtmlReporter(prop.getProperty("EPreport"));
        filepath = prop.getProperty("DataFile");
        URI = prop.getProperty("URI");
        //Login
        usernameL = fr.readExcel(filepath, 0);
        //Edit profile
        nameEP = fr.readExcel(filepath, 3);
        emailEP = fr.readExcel(filepath, 5);
        passwordEP = fr.readExcel(filepath, 4);
        driver = fr.initDriver(prop);
        extent.attachReporter(reporter);
        driver.navigate().to(URI);
    }

    @Test(groups = {"functest"}, priority = 1)
    public void scenario3() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 3", "The user types a correct name");

        dataArray = fr.turnArray(nameEP, 3);
        name = dataArray[0];
        usernameLo = dataArray[1];
        passwordLo = dataArray[2];

        fr.login(usernameLo, passwordLo, driver);

        EditProfile ep = new EditProfile(driver);
        //Profile Picture
        element = ep.getProfilePicture();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Profile Picture Button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());


        //Step 1
        element = ep.getUnlockName();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click unlock name", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 2
        ep.getEditName().sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.END, "");
        ep.getEditName().sendKeys(Keys.BACK_SPACE, name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field of the name \n\r\tData: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        //Step 3
        element = ep.getSaveButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Save button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //check alert
        temp = fr.getScreenshot(driver);
        driver.switchTo().alert().accept();
        logger.pass("Accept the alert.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        varWat = new WebDriverWait(driver, 2);

        //Refresh page
        driver.navigate().refresh();
        temp = fr.getScreenshot(driver);
        logger.pass("Refresh the browser.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());


        varWat = new WebDriverWait(driver, 10);

        //Profile Picture
        element = ep.getProfilePicture();
        varWat.until(ExpectedConditions.elementToBeClickable(element));

        element.click();

        temp = fr.getScreenshot(driver);
        logger.pass("Click on Profile Picture Button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = ep.getUnlockName();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click unlock name", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        Assert.assertEquals(ep.getNameVerified2(),name,"Fail: Data doesn't match //");
    }


    @Test(groups = {"functest"}, priority = 2)
    public void scenario9() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 9", "The user types its correct email");

        dataArray = fr.turnArray(emailEP, 2);
        email = dataArray[0];
        usernameLo = dataArray[1];
        passwordLo = dataArray[2];

        fr.login(usernameLo, passwordLo, driver);

        EditProfile ep = new EditProfile(driver);

        //Profile Picture
        element = ep.getProfilePicture();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Profile Picture Button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());


        //Step 1
        element = ep.getUnlockEmail();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click unlock email", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 2
        ep.getEditEmail().sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.END, "");
        ep.getEditEmail().sendKeys(Keys.BACK_SPACE, email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field of the email \n\r\tData: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        //Step 3
        element = ep.getSaveButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Save button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());


        //check alert
        driver.switchTo().alert().accept();
        temp = fr.getScreenshot(driver);
        logger.pass("Accept the alert.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        varWat = new WebDriverWait(driver, 2);

        //Refresh page
        driver.navigate().refresh();
        temp = fr.getScreenshot(driver);
        logger.pass("Refresh the browser.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());


        varWat = new WebDriverWait(driver, 10);

        //Profile Picture
        element = ep.getProfilePicture();
        varWat.until(ExpectedConditions.elementToBeClickable(element));

        element.click();

        temp = fr.getScreenshot(driver);
        logger.pass("Click on Profile Picture Button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = ep.getUnlockEmail();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click unlock email", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());


        Assert.assertEquals(ep.getEmailVerified2(),email,"Fail: Data does not match // ");

    }

    //////////////////////////           password           ////////////////////////////////
    @Test(groups = {"functest"}, priority = 3)
    public void scenario11() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 11", "The user types a new password with the required characteristics.");

        dataArray = fr.turnArray(passwordEP, 1);
        password = dataArray[0];
        usernameLo = dataArray[1];
        passwordLo = dataArray[2];

        fr.login(usernameLo, passwordLo, driver);

        EditProfile ep = new EditProfile(driver);

        //Profile Picture
        element = ep.getProfilePicture();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Profile Picture Button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());


        //Step 1
        element = ep.getClickPassword();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click 'Change Password'.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 2
        element = ep.getNewPassword();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write the new password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        element = ep.getNewBtnSave();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on 'Save' button.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //check alert
        temp = fr.getScreenshot(driver);
        logger.pass("Alert 'Password updated'.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        driver.switchTo().alert().accept();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        varWat = new WebDriverWait(driver, 2);

        //Step 3
        element = ep.getSaveButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Save button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 3
        element = ep.getBurgerIcon();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Burger button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 3
        element = ep.getLogOut();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Log Out", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        temp = fr.getScreenshot(driver);
        logger.pass("Confirm the Log In with the new password. ", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        fr.login(usernameLo, password, driver);

        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Assert.assertTrue(driver.getCurrentUrl().contains("app/project"),"Password does not match //");

    }

    @AfterMethod(groups = {"functest"})
    public void tearDown(ITestResult result) throws IOException {
        fr.checkAlert(driver);
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
