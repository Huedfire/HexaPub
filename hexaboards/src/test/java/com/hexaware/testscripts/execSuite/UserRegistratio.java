package com.hexaware.testscripts.execSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hexaware.frameworks.gui.GuiFramework;
import com.hexaware.frameworks.gui.pageobjects.HomePage;
import com.hexaware.frameworks.gui.pageobjects.UserRegistration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
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

public class UserRegistratio {
    ExtentReports extent = new ExtentReports();
    ExtentHtmlReporter reporter;
    ExtentTest logger;
    WebDriver driver;
    InputStream input;
    Properties prop = new Properties();
    ArrayList<String> user;
    String[] dataArray;
    String name,email,username,password,confpass,temp,filepath,URI;
    GuiFramework fr = new GuiFramework();

    // This code will run before executing any testcase
    @BeforeMethod(groups = {"functest"})
    public void setup() throws IOException {
        input = new FileInputStream("confs.txt");
        prop.load(input);
        reporter = new ExtentHtmlReporter(prop.getProperty("URreport"));
        filepath = prop.getProperty("DataFile");
        URI = prop.getProperty("URI");
        user = fr.readExcel(filepath, 1);
        driver = fr.initDriver(prop);
        extent.attachReporter(reporter);

        ChromeOptions options = new ChromeOptions();
        options.addArguments();


        driver.navigate().to(URI);
    }

    //////////////////////////////////////////FIRST WAY//////////////////////////////////////////////////////////////////////////
    ///////////////////////////////Scenario 1////////////////////////////////////////////
    @Test(groups = {"functest"})
    public void scenario1() throws IOException, InterruptedException {
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("User registration scenario 1", "User Registration happy path 1");
        dataArray = fr.turnArray(user, 1);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

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
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
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
        Assert.assertTrue(driver.switchTo().alert().getText().contains("User created"),"Does not display a message.  //");
        temp = fr.getScreenshot(driver);
        //fr.checkAlert(driver);
        driver.switchTo().alert().accept();
        logger.pass("Alert 'User created'" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        Assert.assertTrue(driver.getCurrentUrl().contains("start/login"),"Does not redirect to login.  //");
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
        fr.checkAlert(driver);
        driver.close();
    }
}

