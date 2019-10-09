package com.hexaware.testscripts.Login;

import com.hexaware.frameworks.gui.pageobjects.HomePage;
import com.hexaware.frameworks.gui.pageobjects.Login;
import com.hexaware.frameworks.gui.GuiFramework;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
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

public class LoginScripts {
    ExtentReports extent = new ExtentReports();
    ExtentHtmlReporter reporter;
    ExtentTest logger;
    WebDriver driver;
    WebElement element;
    InputStream input;
    Properties prop = new Properties();

    String filepath;
    String URI;
    String browser;

    ArrayList<String> user;
    String[] dataArray;
    String username;
    String password;
    String temp;
    GuiFramework fr = new GuiFramework();

    // This code will run before executing any testcase
    @BeforeMethod(groups = {"functest"})
    public void setup() throws IOException {
        input = new FileInputStream("confs.txt");
        prop.load(input);
        reporter = new ExtentHtmlReporter(prop.getProperty("Logreport"));
        filepath = prop.getProperty("DataFile");
        URI = prop.getProperty("URI");
        user = fr.readExcel(filepath, 0);
        driver = fr.initDriver(prop);
        browser = prop.getProperty("browser");
        extent.attachReporter(reporter);
        driver.navigate().to(URI);
    }

    @Test(groups = {"functest"}, priority = 1)
    public void login1() throws IOException {
        logger = extent.createTest("Scenario 1", "Login happy path");

        dataArray = fr.turnArray(user, 1);
        username = dataArray[0];
        password = dataArray[1];

        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);

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
        logger.pass("Write  in the field the user name \n\r\tData: " + username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //get password
        lg.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //click to login
        lg.getloginButton().click();;
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Log in'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(driver.getCurrentUrl().contains("app/project"),"Does not redirect to another page //");


    }

   /*
    @Test(groups = {"functest"}, priority = 2)
    public void login2() throws IOException {
        logger = extent.createTest("Scenario 2", "Login Fails");
        dataArray = fr.turnArray(user, 2);
        username = dataArray[0];
        password = dataArray[1];
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);

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
        logger.pass("Write  in the field the user name \n\r\tData: " + username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //get password
        lg.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //click to login
        element = lg.getloginButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Log in'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(lg.getPasswordError().isEnabled(),"Does not display a message //");

    }

    @Test(groups = {"functest"}, priority = 3)
    public void login3() throws IOException {
        logger = extent.createTest("Scenario 3", "Login Fails");
        dataArray = fr.turnArray(user, 3);
        username = dataArray[0];
        password = dataArray[1];
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);

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
        logger.pass("Write  in the field the user name \n\r\tData: " + username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //get password
        lg.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //click to login
        element = lg.getloginButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Log in'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(lg.getUsernameError().isEnabled(),"Does not display a message //");

    }

    @Test(groups = {"functest"}, priority = 4)
    public void login4() throws IOException {
        logger = extent.createTest("Scenario 4", "Login Fails");
        dataArray = fr.turnArray(user, 4);
        username = dataArray[0];
        password = dataArray[1];
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);

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
        logger.pass("Write  in the field the user name \n\r\tData: " + username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //get password
        lg.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //click to login
        element = lg.getloginButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Log in'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(driver.getPageSource().contains("Wrong password"),"Does not display a message //");

    }

    @Test(groups = {"functest"}, priority = 5)
    public void login5() throws IOException {
        logger = extent.createTest("Scenario 5", "Login Fails");
        dataArray = fr.turnArray(user, 5);
        username = dataArray[0];
        password = dataArray[1];
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);

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
        logger.pass("Write  in the field the user name \n\r\tData: " + username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //get password
        lg.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //click to login
        element = lg.getloginButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Log in'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(driver.getPageSource().contains("Wrong username / invalid username"),"Does not display a message //");

    }

    @Test(groups = {"functest"}, priority = 6)
    public void login6() throws IOException {
        logger = extent.createTest("Scenario 6", "Login Fails");
        dataArray = fr.turnArray(user, 6);
        username = dataArray[0];
        password = dataArray[1];
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);

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
        logger.pass("Write  in the field the user name \n\r\tData: " + username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //get password
        lg.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //click to login
        element = lg.getloginButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Log in'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(lg.getUsernameError().isEnabled()&&lg.getPasswordError().isEnabled(),"Does not display the messages //");

    }

    @Test(groups = {"functest"}, priority = 7)
    public void login7() throws IOException {
        logger = extent.createTest("Scenario 7", "Login Fails");
        dataArray = fr.turnArray(user, 7);
        username = dataArray[0];
        password = dataArray[1];
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);

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
        logger.pass("Write  in the field the user name \n\r\tData: " + username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //get password
        lg.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //click to login
        element = lg.getloginButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Log in'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(driver.getPageSource().contains("Wrong username / invalid username"),"Does not display a mesaage//");
    }

    @Test(groups = {"functest"}, priority = 8)
    public void login8() throws IOException {
        logger = extent.createTest("Scenario 8", "Login Happy Path 2");
        dataArray = fr.turnArray(user, 1);
        username = dataArray[0];
        password = dataArray[1];
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        element = hp.getStartedButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Get Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //click to login
        element = hp.getLogins();
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on 'Log in' link at  the bottom of the page.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //element name
        lg.getUsername().sendKeys(username);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the user name \n\r\tData: " + username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //get password
        lg.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //click to login
        element = lg.getloginButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Log in'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(driver.getCurrentUrl().contains("/app/project"),"Does not log in the application //");
    }

    @Test(groups = {"functest"}, priority = 9)
    public void login9() throws IOException {
        logger = extent.createTest("Scenario 9", "Login Happy Path 3");
        dataArray = fr.turnArray(user, 1);
        username = dataArray[0];
        password = dataArray[1];
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //click on get started
        element = hp.getStartedButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Get Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //click on Getting Started
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
        logger.pass("Write in the field the user name Data: " + username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //get password
        lg.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        if (browser.equalsIgnoreCase("chrome")) {
            element = lg.getStartedValue();
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().build().perform();

        }
        if(browser.equalsIgnoreCase("firefox")){
            lg.getDocs().sendKeys(Keys.ARROW_UP);
            lg.getRegister().sendKeys(Keys.ARROW_UP);
            lg.getLogin().sendKeys(Keys.ENTER);
        }


        //click login button
        lg.getloginButton().click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Log in'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(driver.getCurrentUrl().contains("/app/project"),"Does not log in the application //");
    }

    @Test(groups = {"functest"}, priority = 10)
    public void login10() throws IOException {
        logger = extent.createTest("Scenario 10", "Forgot Your Username Link");
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //press the button getting started
        element = lg.getStartedValue();
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //press the button login
        element = lg.getLogin();
        element.sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select 'Login' item from the list.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //click on forgot your username
        lg.getForgotUsername().click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on link 'Forgot your username?'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        Assert.assertTrue(!driver.getCurrentUrl().contains("/start/login"),"Does not redirect to another page //");

    }

    @Test(groups = {"functest"}, priority = 11)
    public void login11() throws IOException {
        logger = extent.createTest("Scenario 11", "Forgot Your Password Link");
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //press the button getting started
        element = lg.getStartedValue();
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //press the button login
        element = lg.getLogin();
        element.sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select 'Login' item from the list.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //click on forgot your username
        lg.getForgotPassword().click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on link 'Forgot your password?'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        Assert.assertTrue(!driver.getCurrentUrl().contains("/start/login"),"Does not redirect to another page //");

    }

    @Test(groups = {"functest"},priority = 12)
    public void login12() throws IOException {
        logger = extent.createTest("Scenario 12", "Login With A Google Account");
        dataArray = fr.turnArray(user, 1);
        username = dataArray[0];
        password = dataArray[1];
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);

        WebDriverWait varWat = new WebDriverWait(driver, 10);

        temp = fr.getScreenshot(driver);
        logger.info("Navigate to the URL", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //press the button getting started
        element = lg.getStartedValue();
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Getting Started'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //press the button login
        element = lg.getLogin();
        element.sendKeys(Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select 'Login' item from the list.", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());

        //click on google icon
        lg.getGoogleIcon().click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Google Icon", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(!driver.getCurrentUrl().contains("/start/404")&&!driver.getCurrentUrl().contains("/start/login"),"Does not redirect to another page to log in with google //");
    }

    @Test(groups = {"functest"},priority = 13)
    public void login13() throws IOException {
        logger = extent.createTest("Scenario 13", "Login Fails");
        dataArray = fr.turnArray(user, 8);
        username = dataArray[0];
        System.out.print(username);
        password = dataArray[1];
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);

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
        logger.pass("Write  in the field the user name \n\r\tData: " + username, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //get password
        lg.getPassword().sendKeys(password);
        temp = fr.getScreenshot(driver);
        logger.pass("Write in the field the password Data: " + password, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //click to login
        element = lg.getloginButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on button 'Log in'", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(driver.getPageSource().contains("Wrong username / invalid username"),"Does not display the message //");


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
        driver.close();
    }
}




