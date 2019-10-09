package com.hexaware.testscripts.UserRegistration;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hexaware.frameworks.gui.GuiFramework;
import com.hexaware.frameworks.gui.pageobjects.HomePage;
import com.hexaware.frameworks.gui.pageobjects.UserRegistration;
import org.openqa.selenium.*;
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
import static org.testng.Assert.assertEquals;

public class UserRegistratio {
    ExtentReports extent = new ExtentReports();
    ExtentHtmlReporter reporter;
    ExtentTest logger;
    WebDriver driver;
    InputStream input;
    Properties prop = new Properties();
    WebElement element;
    String filepath;
    String URI;
    ArrayList<String> user;
    String[] dataArray;
    String name,email,username,password,confpass,temp;
    GuiFramework fr = new GuiFramework();
    JavascriptExecutor je = null;

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
    @Test(groups = {"functest"}, priority = 1)
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

   /* ///////////////////////////////Scenario 2////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 2)
    public void scenario2() throws IOException {
        logger = extent.createTest("User registration scenario 2", "User Registration happy path 2");

        dataArray = fr.turnArray(user , 2);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
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
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 8
        //close the getting started and click on terms and conditions checkbox
        ur.getRegister().sendKeys(Keys.ARROW_DOWN);
        ur.getRegister().sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getConfirmPassword().sendKeys(Keys.TAB);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getTermsLocator().sendKeys(Keys.SPACE);
        temp = fr.getScreenshot(driver);
        logger.pass("Click to I agree to the Terms of use & service. :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 9
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

    ///////////////////////////////Scenario 3////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 3)
    public void scenario3() throws IOException {
        logger = extent.createTest("User registration scenario 3", "User Registration happy path 3");

        dataArray = fr.turnArray(user , 3);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
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
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 8
        //close the getting started and click on terms and conditions checkbox
        ur.getRegister().sendKeys(Keys.ARROW_DOWN);
        ur.getRegister().sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getConfirmPassword().sendKeys(Keys.TAB);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getTermsLocator().sendKeys(Keys.SPACE);
        temp = fr.getScreenshot(driver);
        logger.pass("Click to I agree to the Terms of use & service. :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(ur.getButtonCreate().isEnabled(),"The button is not enabled.  //");

        //step 9
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
    ///////////////////////////////Scenario 4////////////////////////////////////////////
    // @Test(groups = {"functest"}, priority = 4)
    public void scenario4() throws IOException {
        logger = extent.createTest("User registration scenario 4", "A user is registered with the Wrong Full name (256+ characters).");

        dataArray = fr.turnArray(user , 4);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
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
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to create an account" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!fr.isAlertPresent(driver) && driver.getCurrentUrl().contains("start/register"),"Full Name field permit 256+ characters. // ");

        if (fr.isAlertPresent(driver))
            Assert.assertFalse(driver.switchTo().alert().getText().contains("User created"),"The application allows to create an existing user. // ");

    }
    ///////////////////////////////Scenario 5////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 5)
    public void scenario5() throws IOException {
        logger = extent.createTest("User registration scenario 5", "The user doesn't fill the name");

        dataArray = fr.turnArray(user , 5);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name        Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email        Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username         Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password         Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password               Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +"create an account" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(ur.getNameError().isDisplayed(),"Does not display an error message.  //");

    }
    ///////////////////////////////Scenario 6////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 6)
    public void scenario6() throws IOException {
        logger = extent.createTest("User registration scenario 6", "The user types a wrong format email");

        dataArray = fr.turnArray(user , 6);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
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
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(ur.getemailError().isDisplayed(),"Does not display an error message.  //");
    }
    ///////////////////////////////Scenario 7////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 7)
    public void scenario7() throws IOException {
        logger = extent.createTest("User registration scenario 7", "The user types a wrong format email");

        dataArray = fr.turnArray(user , 7);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
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
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to create an account" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(ur.getemailError().isDisplayed(),"Does not display an error message.  //");
    }
    ///////////////////////////////Scenario 8/////////////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 8)
    public void scenario8() throws IOException {
        logger = extent.createTest("User registration scenario 8", "The user types a wrong format email");

        dataArray = fr.turnArray(user , 8);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
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
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to create an account" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(ur.getemailError().isDisplayed(),"Does not display an error message.  //");
    }

    ///////////////////////////////Scenario 9////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 9)
    public void scenario9() throws IOException {
        logger = extent.createTest("User registration scenario 9", "The user types a wrong format email");

        dataArray = fr.turnArray(user , 9);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
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
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(ur.getemailError().isDisplayed(),"Does not display an error message.  //");

    }
    ///////////////////////////////Scenario 10////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 10)
    public void scenario10() throws IOException {
        logger = extent.createTest("User registration scenario 10", "The user doesn't type the email");

        dataArray = fr.turnArray(user , 10);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
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
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(ur.getemailError().isDisplayed(),"Does not display an error message.  //");
    }
    ///////////////////////////////Scenario 11////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 11)
    public void scenario11() throws IOException {
        logger = extent.createTest("User registration scenario 11", "The user types its username with blank space");

        dataArray = fr.turnArray(user , 11);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
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
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!fr.isAlertPresent(driver) && driver.getCurrentUrl().contains("start/register"),"The field allows a space as a Full name.  // ");

        if (fr.isAlertPresent(driver))
            Assert.assertFalse(driver.switchTo().alert().getText().contains("User created"),"The application allows to create an existing user. // ");

    }
    ///////////////////////////////Scenario 12////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 12)
    public void scenario12() throws IOException {
        logger = extent.createTest("User registration scenario 12", "The username doesn't have the minimum of characters");

        dataArray = fr.turnArray(user , 12);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
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
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!fr.isAlertPresent(driver) && driver.getCurrentUrl().contains("start/register"),"The username allows 6- characters.  // ");

        if (fr.isAlertPresent(driver))
            Assert.assertFalse(driver.switchTo().alert().getText().contains("User created"),"The application allows to create an existing user. // ");

    }
    ///////////////////////////////Scenario 13////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 13)
    public void scenario13() throws IOException {
        logger = extent.createTest("User registration scenario 13", "Username contains more than 40 characters.");

        dataArray = fr.turnArray(user , 13);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
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
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(ur.getusernameError().isDisplayed(),"Does not display an error message.  //");
    }
    ///////////////////////////////Scenario 14////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 14)
    public void scenario14() throws IOException {
        logger = extent.createTest("User registration scenario 14", "The field username is in blank");

        dataArray = fr.turnArray(user , 14);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
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
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(ur.getusernameError().isDisplayed(), "Does not display an error message.  //");
    }
    ///////////////////////////////Scenario 15////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 15)
    public void scenario15() throws IOException {
        logger = extent.createTest("User registration scenario 15", "Username contains special characters not allowed");

        dataArray = fr.turnArray(user , 15);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
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
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!fr.isAlertPresent(driver) && driver.getCurrentUrl().contains("start/register"),"Username allows special characters.  // ");

        if (fr.isAlertPresent(driver))
            Assert.assertFalse(driver.switchTo().alert().getText().contains("User created"),"The application allows to create an existing user. // ");

    }
    ///////////////////////////////Scenario 16////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 16)
    public void scenario16() throws IOException {
        logger = extent.createTest("User registration scenario 16", "Username contains special characters not allowed");

        dataArray = fr.turnArray(user , 16);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +
                "create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!fr.isAlertPresent(driver) && driver.getCurrentUrl().contains("start/register"),"Username allows special characters.  // ");

        if (fr.isAlertPresent(driver))
            Assert.assertFalse(driver.switchTo().alert().getText().contains("User created"),"The application allows to create an existing user. // ");

    }
    ///////////////////////////////Scenario 17////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 17)
    public void scenario17() throws IOException {
        logger = extent.createTest("User registration scenario 17", "Username contains special characters not allowed");

        dataArray = fr.turnArray(user , 17);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +
                "create an account :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!fr.isAlertPresent(driver) && driver.getCurrentUrl().contains("start/register"),"Username allows special characters.  // ");

        if (fr.isAlertPresent(driver))
            Assert.assertFalse(driver.switchTo().alert().getText().contains("User created"),"The application allows to create an existing user. // ");

    }
    ///////////////////////////////Scenario 18////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 18)
    public void scenario18() throws IOException {
        logger = extent.createTest("User registration scenario 18", "Username contains special characters not allowed");

        dataArray = fr.turnArray(user , 18);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 8
        //close the getting started and click on terms and conditions checkbox
        ur.getRegister().sendKeys(Keys.ARROW_DOWN);
        ur.getRegister().sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getConfirmPassword().sendKeys(Keys.TAB);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getTermsLocator().sendKeys(Keys.SPACE);
        temp = fr.getScreenshot(driver);
        logger.pass("Click to I agree to the Terms of use & service. :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 9
        ur.getButtonCreate().click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +
                "create an account :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!fr.isAlertPresent(driver) && driver.getCurrentUrl().contains("start/register"),"Username allows special characters.  // ");

        if (fr.isAlertPresent(driver))
            Assert.assertFalse(driver.switchTo().alert().getText().contains("User created"),"The application allows to create an existing user. // ");

    }
    ///////////////////////////////Scenario 19////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 19)
    public void scenario19() throws IOException {
        logger = extent.createTest("User registration scenario 19", "The password does not comply with the specified characteristics");

        dataArray = fr.turnArray(user , 19);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 8
        //close the getting started and click on terms and conditions checkbox
        ur.getRegister().sendKeys(Keys.ARROW_DOWN);
        ur.getRegister().sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getConfirmPassword().sendKeys(Keys.TAB);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getTermsLocator().sendKeys(Keys.SPACE);
        temp = fr.getScreenshot(driver);
        logger.pass("Click to I agree to the Terms of use & service. :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 9
        ur.getButtonCreate().click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +
                "create an account :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!fr.isAlertPresent(driver) && driver.getCurrentUrl().contains("start/register"),"The field password allows an invalid format.  // ");

        if (fr.isAlertPresent(driver))
            Assert.assertFalse(driver.switchTo().alert().getText().contains("User created"),"The application allows to create an existing user. // ");

    }
    ///////////////////////////////Scenario 20////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 20)
    public void scenario20() throws IOException {
        logger = extent.createTest("User registration scenario 20", "The password contains more characters than allowed");

        dataArray = fr.turnArray(user , 20);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +
                "create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!fr.isAlertPresent(driver) && driver.getCurrentUrl().contains("start/register"),"Password field permit more than 16 characters. //  ");

        if (fr.isAlertPresent(driver))
            Assert.assertFalse(driver.switchTo().alert().getText().contains("User created"),"The application allows to create an existing user. // ");

    }
    ///////////////////////////////Scenario 21////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 21)
    public void scenario21() throws IOException {
        logger = extent.createTest("User registration scenario 21", "The password does not comply with the specified characteristics");

        dataArray = fr.turnArray(user , 21);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +
                "create an account :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!fr.isAlertPresent(driver) && driver.getCurrentUrl().contains("start/register"),"Password field permit a wrong format.  // ");

        if (fr.isAlertPresent(driver))
            Assert.assertFalse(driver.switchTo().alert().getText().contains("User created"),"The application allows to create an existing user. // ");

    }
    ///////////////////////////////Scenario 22////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 22)
    public void scenario22() throws IOException {
        logger = extent.createTest("User registration scenario 22", "The field password is in blank");

        dataArray = fr.turnArray(user , 22);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +
                "create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(ur.getPasswordError().isDisplayed(), "Does not display an error message.  //");
    }
    ///////////////////////////////Scenario 23////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 23)
    public void scenario23() throws IOException {
        logger = extent.createTest("User registration scenario 23", "The passwords do not match");

        dataArray = fr.turnArray(user , 23);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +
                "create an account" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(driver.switchTo().alert().getText().contains("Passwords don't match"),"Does not display a message.  // ");
    }
    ///////////////////////////////Scenario 24////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 24)
    public void scenario24() throws IOException {
        logger = extent.createTest("User registration scenario 24", "The confirm password field is in blank");

        dataArray = fr.turnArray(user , 24);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 8
        //close the getting started and click on terms and conditions checkbox
        ur.getRegister().sendKeys(Keys.ARROW_DOWN);
        ur.getRegister().sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getConfirmPassword().sendKeys(Keys.TAB);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getTermsLocator().sendKeys(Keys.SPACE);
        temp = fr.getScreenshot(driver);
        logger.pass("Click to I agree to the Terms of use & service. :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 9
        ur.getButtonCreate().click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +
                "create an account :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(ur.getConfPassError().isDisplayed(), "Does not display a message. // ");
    }
    ///////////////////////////////Scenario 25////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 25)
    public void scenario25() throws IOException {
        logger = extent.createTest("User registration scenario 25", "Checkbox terms and conditions were not accepted");

        dataArray = fr.turnArray(user , 25);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 8
        //close the getting started and click on terms and conditions checkbox
        ur.getRegister().sendKeys(Keys.ARROW_DOWN);
        ur.getRegister().sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getConfirmPassword().sendKeys(Keys.TAB);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        temp = fr.getScreenshot(driver);
        logger.pass("Does not click to 'I agree to the Terms of use & service'. " , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(!ur.getTermsLocator().isSelected(),"The check box is selected. // ");
        ur.getButtonCreate().click();

        Assert.assertTrue(!ur.getButtonCreate().isEnabled(),"The  button 'Create user' is enabled. // ");
    }
    ///////////////////////////////Scenario 26////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 26)
    public void scenario26() throws IOException {
        logger = extent.createTest("User registration scenario 26", "The username already in use");

        dataArray = fr.turnArray(user , 26);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 8
        //close the getting started and click on terms and conditions checkbox
        ur.getRegister().sendKeys(Keys.ARROW_DOWN);
        ur.getRegister().sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getConfirmPassword().sendKeys(Keys.TAB);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getTermsLocator().sendKeys(Keys.SPACE);
        temp = fr.getScreenshot(driver);
        logger.pass("Click to I agree to the Terms of use & service. :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 9
        ur.getButtonCreate().click();

        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +
                "create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!fr.isAlertPresent(driver) && driver.getCurrentUrl().contains("start/register"),"The application allows to create an existing user. // ");

        if (fr.isAlertPresent(driver))
            Assert.assertFalse(driver.switchTo().alert().getText().contains("User created"),"The application allows to create an existing user. // ");

    }
    ///////////////////////////////Scenario 27////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 27)
    public void scenario27() throws IOException {
        logger = extent.createTest("User registration scenario 27", "The user name contains only numbers");

        dataArray = fr.turnArray(user , 27);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +
                "create an account :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        /////////////Alert////////////////////////////////////
        Assert.assertTrue(driver.switchTo().alert().getText().contains("User created"),"Does not display a message. //");
        temp = fr.getScreenshot(driver);
        fr.checkAlert(driver);
        logger.pass("Alert 'User created'" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        Assert.assertTrue(driver.getCurrentUrl().contains("start/login"),"Does not redirect to login page. //");
    }
    ///////////////////////////////Scenario 28////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 28)
    public void scenario28() throws IOException {
        logger = extent.createTest("User registration scenario 28", "The  user is registered with Google account");

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        varWat.until(ExpectedConditions.elementToBeClickable(ur.getRegisterG()));
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Google'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(driver.getCurrentUrl().contains("google.com"),"Does not redirect to google page. // ");
    }
    ///////////////////////////////Scenario 29////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 29)
    public void scenario29() throws IOException {
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("User registration scenario 29", "User Registration happy path 4");
        dataArray = fr.turnArray(user, 28);
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
        varWat.until(ExpectedConditions.elementToBeClickable(hp.getStartedButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Get Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //Step 2
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 3
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 4
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: " + username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 5
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getConfirmPassword().sendKeys(Keys.TAB);
        //step 7
        ur.getConfirmPassword().sendKeys(Keys.TAB);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ur.getTermsLocator().sendKeys(Keys.SPACE);
        temp = fr.getScreenshot(driver);
        logger.pass("Click to I agree to the Terms of use & service. :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 8
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
    //////////////////////////////////////////SECOND WAY (Scenario 30)//////////////////////////////////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 30)
    public void scenario30() throws IOException, InterruptedException {
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("User registration scenario 30", "User Registration happy path 5");
        dataArray = fr.turnArray(user, 29);
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
        varWat.until(ExpectedConditions.elementToBeClickable(hp.getStartedButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Get Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
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
        logger.pass("Click to I agree to the Terms of use & service. :" , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
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

        //  Assert.assertTrue(driver.getCurrentUrl().contains("start/login"),"Does not redirect to login.  //");
        Assert.assertTrue(ur.getLoginText().isDisplayed());
    }
    /////////////////////////////THIRD WAY (Scenario 31)/////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 31)
    public void scenario31() throws IOException, InterruptedException {
        logger = extent.createTest("User registration scenario 31", "All fields are filled with 6 spaces.");

        dataArray = fr.turnArray(user , 30);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +
                "create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!fr.isAlertPresent(driver) && driver.getCurrentUrl().contains("start/register"),"The user is registered. Admit spaces in the fields.  // ");

        if (fr.isAlertPresent(driver))
            Assert.assertFalse(driver.switchTo().alert().getText().contains("User created"),"The application allows to create an existing user. // ");

    }

    ///////////////////////////////Scenario 32////////////////////////////////////////////
    @Test(groups = {"functest"}, priority = 32)
    public void scenario32() throws IOException {
        logger = extent.createTest("User registration scenario 32", "All fields are filled with 6 spaces and @ in email field.");

        dataArray = fr.turnArray(user , 31);
        name = dataArray[0];
        email = dataArray[1];
        username = dataArray[2];
        password = dataArray[3];
        confpass = dataArray[4];

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //step 1
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //step 2
        ur.getRegister().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Register'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 3
        ur.getName().sendKeys(name);
        temp = fr.getScreenshot(driver);
        logger.pass("Write  in the field the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 4
        ur.getEmail().sendKeys(email);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the email Data: " + email, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 5
        ur.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field username Data: "+ username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 6
        ur.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field password Data: "+ password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 7
        ur.getConfirmPassword().sendKeys(confpass);
        temp = fr.getScreenshot(driver);
        logger.pass("Confirm your password Data:" + confpass, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
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
        temp = fr.getScreenshot(driver);
        logger.pass("Click to " +
                "create an account." , MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!fr.isAlertPresent(driver) && driver.getCurrentUrl().contains("start/register"),"The user is registered. Admit spaces in the fields.  // ");

        if (fr.isAlertPresent(driver))
            Assert.assertFalse(driver.switchTo().alert().getText().contains("User created"),"The application allows to create an existing user. // ");

    }

    */

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

