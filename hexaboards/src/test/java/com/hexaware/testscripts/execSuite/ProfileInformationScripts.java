package com.hexaware.testscripts.execSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hexaware.frameworks.gui.GuiFramework;
import com.hexaware.frameworks.gui.pageobjects.HomePage;
import com.hexaware.frameworks.gui.pageobjects.Login;
import com.hexaware.frameworks.gui.pageobjects.ProfileInformation;
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


public class ProfileInformationScripts {
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
        reporter = new ExtentHtmlReporter(prop.getProperty("PIreport"));
        filepath = prop.getProperty("DataFile");
        URI = prop.getProperty("URI");
        user = fr.readExcel(filepath, 7);
        driver = fr.initDriver(prop);
        extent.attachReporter(reporter);
        driver.navigate().to(URI);
    }

    @Test(groups = {"functest"})
    public void scenario1() throws IOException, InterruptedException {
        logger = extent.createTest("Scenario 1", "View the profile information");
        dataArray = fr.turnArray(user, 1);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        email = dataArray[3];
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);
        ProfileInformation pi = new ProfileInformation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        element = hp.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));

        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        //press the button login
        lg.getLogin().sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select 'Login' item from the list.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
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
        Assert.assertTrue(pi.getVerifiedName(name).isDisplayed() && pi.getVerifiedEmail(email).isDisplayed());
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
