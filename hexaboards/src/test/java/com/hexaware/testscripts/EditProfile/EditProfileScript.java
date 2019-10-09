package com.hexaware.testscripts.EditProfile;

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
    @BeforeMethod(groups = {"positive","negative"})
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

    //////////////////////////////////////////FIRST WAY//////////////////////////////////////////////////////////////////////////

 /*   ////////////////////////////////////////Name's scenarios////////////////////////////////////////////////////////////////
    @Test(groups = {"negative"}, priority = 1)
    public void scenario1() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 1", "The user doesn’t fill the field name");

        dataArray = fr.turnArray(nameEP, 1);
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
        logger.pass("Click Profile Picture Button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 1
        element = ep.getUnlockName();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click unlock name", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        ep.getEditName().sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.END, "");
        ep.getEditName().sendKeys(Keys.BACK_SPACE, name);
        temp = fr.getScreenshot(driver);
        logger.pass("Clear name field.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //Step 2
        element = ep.getSaveButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click Save button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        Assert.assertTrue(ep.getBlankErrorName().isEnabled(), "An error message must be displayed. //");
    }
*/
  /*  @Test(groups = {"negative"}, priority = 2)
    public void scenario2() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 2", "The user fill the field name with a blank space");

        dataArray = fr.turnArray(nameEP, 2);
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
        logger.pass("Write in the field of the name Data: " + name, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        //Step 3
        element = ep.getSaveButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Save button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        boolean isAlert = false;
        try{
            if (driver.switchTo().alert().getText().contains("Name changed") || ep.getBlankErrorName().isEnabled() ){
                isAlert = true;
            }
        } catch (Exception e){ }


        Assert.assertTrue(!isAlert, "Does not display an error message. //");

    }
*/
    @Test(groups = {"positive"}, priority = 3)
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

/*
    @Test(groups = {"negative"}, priority = 4)
    public void scenario4() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 4", "The user types its name with more characters than allowed");

        dataArray = fr.turnArray(nameEP, 4);
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

        Assert.assertTrue(ep.getBlankErrorName().isDisplayed(),"An error message is not displayed. //");

    }
*/
  /*  @Test(groups = {"negative"}, priority = 5)
    public void scenario5() throws IOException, InterruptedException {
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 5", "The user types its name with numbers");
        dataArray = fr.turnArray(nameEP, 5);
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
        driver.switchTo().alert().accept();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        varWat = new WebDriverWait(driver, 2);
        //Refresh page
        driver.navigate().refresh();

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
        Assert.assertEquals(ep.getNameVerified2(),name,"Fail: Data does not match //");
    }

    @Test(groups = {"negative"}, priority = 6)
    public void scenario6() throws IOException, InterruptedException {
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 6", "The user types its name with special characters");
        dataArray = fr.turnArray(nameEP, 6);
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

        Assert.assertTrue(ep.getBlankErrorName().isDisplayed(),"Does not display the message 'Use the correct format'. //");
    }

    @Test(groups = {"negative"}, priority = 7)
    public void scenario7() throws IOException, InterruptedException {
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 7", "The  user types its name with a number and a special character");
        dataArray = fr.turnArray(nameEP, 7);
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

        Assert.assertTrue(ep.getBlankErrorName().isDisplayed(),"Does not display the message 'Use the correct format'. //");
    }
/////////////////////// Email's scenarios////////////////////////////////////////////////////////////////////////////

    @Test(groups = {"negative"}, priority = 8)
    public void scenario8() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 8", "The user doesn’t fill the field email ");

        dataArray = fr.turnArray(emailEP, 1);
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

//div[@aria-labelledby="mat-expansion-panel-header-0"]//span[contains(text(),"Edit Role")]
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

        Assert.assertTrue(ep.getBlankErrorEmail().isDisplayed(), "Does not display a message. // ");
    }
*/
    @Test(groups = {"positive"}, priority = 9)
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
/*
    @Test(groups = {"negative"}, priority = 10)
    public void scenario10() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 10", "The user types its email in wrong format");

        dataArray = fr.turnArray(emailEP, 3);
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


        Assert.assertTrue(ep.getBlankErrorEmail().isDisplayed(),"Does not display a message. // ");
    }
*/
    //////////////////////////           password           ////////////////////////////////
    @Test(groups = {"positive"}, priority = 11)
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
/*
    @Test(groups = {"negative"}, priority = 12)
    public void scenario12() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 12", "The user types a password that include Special characters");

        dataArray = fr.turnArray(passwordEP, 2);
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

        Assert.assertTrue(ep.getPassError().isDisplayed(),"Does not display a message. //");

    }

    @Test(groups = {"negative"}, priority = 14)
    public void scenario14() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 14", "The user types a password that include Only numbers");

        dataArray = fr.turnArray(passwordEP, 4);
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

        Assert.assertTrue(ep.getPassError().isDisplayed(),"Does not display a message. //");

    }

    @Test(groups = {"negative"}, priority = 13)
    public void scenario13() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 13", "The user types a password that include A capital letter");

        dataArray = fr.turnArray(passwordEP, 3);
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

        Assert.assertTrue(ep.getPassError().isDisplayed(),"Does not display a message. //");

    }

    @Test(groups = {"negative"}, priority = 15)
    public void scenario15() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 15", "The user types a password that include Special characters and a capital letter");

        dataArray = fr.turnArray(passwordEP, 5);
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

        Assert.assertTrue(ep.getPassError().isDisplayed(),"Does not display a message. //");

    }

    @Test(groups = {"negative"}, priority = 16)
    public void scenario16() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 16", "The user types a password that include Special characters and numbers");

        dataArray = fr.turnArray(passwordEP, 6);
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

        Assert.assertTrue(ep.getPassError().isDisplayed(),"Does not display a message. //");

    }

    @Test(groups = {"negative"}, priority = 17)
    public void scenario17() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 17", "The user types a password that include Special characters and numbers");

        dataArray = fr.turnArray(passwordEP, 7);
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

        Assert.assertTrue(ep.getPassError().isDisplayed(),"Does not display a message. //");

    }

    /////////////////   email again   /////////////////////////
    @Test(groups = {"negative"}, priority = 18)
    public void scenario18() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 18", "The user types into the email field a blank space");

        dataArray = fr.turnArray(emailEP, 4);
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

        Assert.assertTrue(ep.getBlankErrorEmail().isDisplayed(),"Does not display a message. // ");

    }

    @Test(groups = {"negative"}, priority = 19)
    public void scenario19() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 19", "The user types an existing email in the DB.");

        dataArray = fr.turnArray(emailEP, 5);
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

        Assert.assertFalse(driver.switchTo().alert().getText().contains("Email changed"), "The application change the email. //");
    }

    @Test(groups = {"negative"}, priority = 20)
    public void scenario20() throws IOException, InterruptedException {

        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 20", "The user types into the email field a blank space, “@” & a dot.");

        dataArray = fr.turnArray(emailEP, 6);
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

        Assert.assertTrue(ep.getBlankErrorEmail().isDisplayed(),"Does not display a message. // ");

    }

 */

    @AfterMethod(groups = {"positive","negative"})
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
