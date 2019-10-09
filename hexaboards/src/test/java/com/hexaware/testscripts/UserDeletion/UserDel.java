package com.hexaware.testscripts.UserDeletion;

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

public class UserDel {
    ExtentReports extent = new ExtentReports();
    ExtentHtmlReporter reporter;
    ExtentTest logger;
    WebDriver driver;
    WebElement element;
    InputStream input;
    Properties prop = new Properties();
    ArrayList<String> user;
    String[] dataArray;
    String name, email,username,password,temp,filepath,URI;
    GuiFramework fr = new GuiFramework ();

    @BeforeMethod(groups = {"functest"})
    public void setup() throws IOException {
        input = new FileInputStream("confs.txt");
        prop.load(input);
        reporter = new ExtentHtmlReporter(prop.getProperty("UDreport2"));
        filepath = prop.getProperty("DataFile");
        URI = prop.getProperty("URI");
        user = fr.readExcel(filepath, 8);
        driver = fr.initDriver(prop);
        extent.attachReporter(reporter);
        driver.navigate().to(URI);
    }

    @Test(groups = {"functest"}, priority = 1)
    public void scenario1() throws IOException, InterruptedException {
        logger = extent.createTest("Scenario 1", "Happy Path");
        Login lg = new Login(driver);
        ProfileInformation pi = new ProfileInformation(driver);
        EditProfile ep = new EditProfile(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);

        dataArray = fr.turnArray(user, 1);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);
        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        varWat.until(ExpectedConditions.elementToBeClickable(hp.getStartedValue())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
        ur.getRegister().sendKeys(Keys.ARROW_DOWN);
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: " + username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getConfirmPassword().sendKeys(Keys.TAB);
        //step 8
        //close the getting started and click on terms and conditions checkbox
        ur.getRegister().sendKeys(Keys.ARROW_DOWN);
        ur.getRegister().sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getConfirmPassword().sendKeys(Keys.TAB);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getTermsLocator().sendKeys(Keys.SPACE);
        temp = fr.getScreenshot(driver);
        logger.pass("Click to I agree to the Terms of use & service." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 9
        ur.getButtonCreate().click();
        //Wait 10 seconds for an alert
        Wait wait2 = new FluentWait(driver)
                .withTimeout(5, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(Exception.class);
        temp = fr.getScreenshot(driver);
        logger.pass("Click to create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        /////////////Alert////////////////////////////////////
        driver.switchTo().alert().accept();
        logger.pass("Alert 'User created' and redirect to Login");

        //----------------------------LOGIN---------------------------------------

        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //element name
        lg.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the user name Data: " + username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //get password
        lg.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //click to login
        lg.getloginButton().click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Log in'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //--------------------------------------------Profile Information----------------------------------------------------------

        //press the burguer button
        varWat.until(ExpectedConditions.visibilityOf(pi.getBurguerButton())).click();
        //  pi.getMenuOptions().click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on burguer button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //click on myProfile button
        pi.getProfileButton().click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on the my profile button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //--------------------------------------------Delete User----------------------------------------------------------
        //click on "Delete accoount" button
        varWat.until(ExpectedConditions.visibilityOf(ep.getDeleteButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on 'Delete account' button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //Write the password
        varWat.until(ExpectedConditions.visibilityOf(ep.getFieldPwd())).sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write the password in the field.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //click on "Delete" button
        varWat.until(ExpectedConditions.visibilityOf(ep.getDeleteButton2())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on 'Delete' button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //Accept alert
        temp = fr.getScreenshot(driver);
        logger.pass("Accept alert", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        try {
             driver.switchTo().alert().accept();
        } catch (Exception e){};

        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

        //----------------------------Login--------------------------
        varWat.until(ExpectedConditions.elementToBeClickable(lg.getLogo()));
        fr.login(username,password,driver);

        boolean flag = false;
        try {
            flag = driver.switchTo().alert().getText().contains("User not found");
        } catch (Exception e){};

        Assert.assertTrue(flag, "Does not display a message. // ");

    }

    @AfterMethod(groups = {"functest","positive","negative"})
    public void after(ITestResult result) throws IOException {
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
