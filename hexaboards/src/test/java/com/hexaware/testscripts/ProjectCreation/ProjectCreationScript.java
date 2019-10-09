package com.hexaware.testscripts.ProjectCreation;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.hexaware.frameworks.gui.GuiFramework;
import com.hexaware.frameworks.gui.pageobjects.HomePage;
import com.hexaware.frameworks.gui.pageobjects.Login;
import com.hexaware.frameworks.gui.pageobjects.ProjectCreation;
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

public class ProjectCreationScript {
    ExtentReports extent = new ExtentReports();
    ExtentHtmlReporter reporter ;
    ExtentTest logger;
    WebDriver driver;
    WebElement element;
    InputStream input;
    Properties prop = new Properties();
    String filepath;
    String URI;
    ArrayList<String> user;
    String[] dataArray;
    String username, password, name, email, temp,  description, start_date, end_date, role, usernameRole;
    GuiFramework fr = new GuiFramework();
    @BeforeMethod(groups = {"functest"})
    public void setup() throws IOException {
        input = new FileInputStream("confs.txt");
        prop.load(input);
        reporter = new ExtentHtmlReporter(prop.getProperty("PCreport2"));
        filepath = prop.getProperty("DataFile");
        URI = prop.getProperty("URI");
        user = fr.readExcel(filepath, 6);
        driver = fr.initDriver(prop);
        extent.attachReporter(reporter);
        driver.navigate().to(URI);
    }

    //////////////////////////////////////////////////SCENARIO 1////////////////////////////////////////////////////////////////////////
    @Test(groups = {"functest"} , priority =1)
    public void scenario1() throws IOException, InterruptedException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 1", "Send a request with a correct information");
        dataArray = fr.turnArray(user, 1);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        //press the button create project on the landing page
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        /////Description//////////
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        //////////CLICK AL CALENDARIO///////////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getCalendarIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Start Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////////Select day of start day///////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDay())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select start day", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////////Select end date////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDate())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDateCalendar())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on End Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDayE())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select end date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);

        //Wait 10 seconds for an alert
        Wait wait2 = new FluentWait(driver)
                .withTimeout(5, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(Exception.class);

        Assert.assertTrue(driver.switchTo().alert().getText().contains("Project created"));
        driver.switchTo().alert().accept();

    }


    //////////////////////////////////////////////////SCENARIO 2////////////////////////////////////////////////////////////////////////
    @Test(groups = {"functest"},priority = 2)
    public void scenario2() throws IOException, InterruptedException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 2", "Send a request with a correct information");
        dataArray = fr.turnArray(user, 2);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        //press the button create project on the landing page
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Description
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //click on roles
        varWat.until(ExpectedConditions.visibilityOf(pc.getRole())).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on roles", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //select member of role
        pc.getUsername().sendKeys(usernameRole);

        varWat.until(ExpectedConditions.visibilityOf(pc.getRole())).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select a member", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        ////////Click Add member///////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getAddButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Add member", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
        //////////CLICK AL CALENDARIO///////////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getCalendarIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Start Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////////Select day of start day///////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDay())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select start day", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////////Select end date////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDate())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDateCalendar())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on End Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDayE())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select end date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        new FluentWait(driver)
                .withTimeout(2,TimeUnit.SECONDS)
                .pollingEvery(2,TimeUnit.SECONDS)
                .ignoring(Exception.class);

        Assert.assertTrue(driver.switchTo().alert().getText().contains("Project created"));
        driver.switchTo().alert().accept();
    }



/*
 //////////////////////////////SCENARIO 3////////////////////////////////////////////////////////////////////////////////

    @Test(groups = {"functest"} , priority =3)
    public void scenario3() throws IOException, InterruptedException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 3", "Send a request with a correct information");
        dataArray = fr.turnArray(user, 3);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        //press the burguer button
        varWat.until(ExpectedConditions.visibilityOf(pc.getOptionIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on burguer button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getNewProjectBar().isDisplayed());

        //press the myproject button
        varWat.until(ExpectedConditions.visibilityOf(pc.getNewProjectBar())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create project button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());


        //ingresar nombre del proyecto
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //Description
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //////////CLICK AL CALENDARIO///////////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getCalendarIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Start Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////////Select day of start day///////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDay())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select start day", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////////Select end date////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDate())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDateCalendar())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on End Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDayE())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select end date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        new FluentWait(driver)
                .withTimeout(2,TimeUnit.SECONDS)
                .pollingEvery(2,TimeUnit.SECONDS)
                .ignoring(Exception.class);

        String x = driver.switchTo().alert().getText();
        boolean gui= (driver.switchTo().alert().getText().contains("Project created succesfully"));
        driver.switchTo().alert().accept();
        fr.checkAlert(driver);
        Assert.assertTrue(gui);
    }

    //////////////////////////////SCENARIO 4////////////////////////////////////////////////////////////////////////////////

    @Test(groups = {"functest"} , priority =4)
    public void scenario4() throws IOException, InterruptedException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 4", "Send a request with a correct information");
        dataArray = fr.turnArray(user, 3);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        //press the burguer button
        varWat.until(ExpectedConditions.visibilityOf(pc.getOptionIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on burguer button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getNewProjectBar().isDisplayed());

        //press the myproject button
        varWat.until(ExpectedConditions.visibilityOf(pc.getNewProjectBar())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create project button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());


        //ingresar nombre del proyecto
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //Description
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //click on roles
        varWat.until(ExpectedConditions.visibilityOf(pc.getRole())).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Click on roles", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        //select member of role
        pc.getUsername().sendKeys(usernameRole);

        varWat.until(ExpectedConditions.visibilityOf(pc.getRole())).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select a member", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);


        //////////CLICK AL CALENDARIO///////////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getCalendarIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Start Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////////Select day of start day///////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDay())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select start day", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////////Select end date////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDate())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDateCalendar())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on End Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDayE())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select end date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        new FluentWait(driver)
                .withTimeout(2,TimeUnit.SECONDS)
                .pollingEvery(2,TimeUnit.SECONDS)
                .ignoring(Exception.class);

        String x = driver.switchTo().alert().getText();
        boolean gui= (driver.switchTo().alert().getText().contains("Project created succesfully"));
        driver.switchTo().alert().accept();
        fr.checkAlert(driver);
        Assert.assertTrue(gui);
    }


    //////////////////////////////////////////////////SCENARIO 6////////////////////////////////////////////////////////////////////////
    @Test(groups = {"functest"} , priority =6)
    public void scenario6() throws IOException, InterruptedException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 6", "Send a request with a correct information");
        dataArray = fr.turnArray(user, 6);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        //press the button create project on the landing page
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        //name
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Description
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //////////CLICK AL CALENDARIO///////////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getCalendarIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Start Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////////Select day of start day///////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDay())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select start day", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////////Select end date////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDate())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDateCalendar())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on End Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDayE())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select end date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        new FluentWait(driver)
                .withTimeout(2,TimeUnit.SECONDS)
                .pollingEvery(2,TimeUnit.SECONDS)
                .ignoring(Exception.class);

        Assert.assertTrue(pc.getnameError().isDisplayed());
    }



    //////////////////////////////////////////////////SCENARIO 7////////////////////////////////////////////////////////////////////////
    @Test(groups = {"functest"} , priority =7)
    public void scenario7() throws IOException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 7", "Create a name with fewer characters than allowed");
        dataArray = fr.turnArray(user, 7);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        //press the button create project on the landing page
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        //Wait 10 seconds for an alert
        Wait wait2 = new FluentWait(driver)
                .withTimeout(5, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(Exception.class);

        Assert.assertTrue(Integer.parseInt(pc.getMinLengthName())==6, "The minimum number of characters accepted in the name field is not 6. // ");
    }

    //////////////////////////////////////////////////SCENARIO 8////////////////////////////////////////////////////////////////////////
    @Test(groups = {"functest"} , priority =8)
    public void scenario8() throws IOException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 8", "Create a project name with more characters than allowed");
        dataArray = fr.turnArray(user, 8);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        //press the button create project on the landing page
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        //Wait 10 seconds for an alert
        Wait wait2 = new FluentWait(driver)
                .withTimeout(5, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(Exception.class);

        Assert.assertTrue(Integer.parseInt(pc.getMaxLengthName())==60,"Does not accept 60 characters .");
    }


    //////////////////////////////////////////////////SCENARIO 9////////////////////////////////////////////////////////////////////////
    @Test(groups = {"functest"} , priority =9)
    public void scenario9() throws IOException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 9", "Try to create a project without description");
        dataArray = fr.turnArray(user, 9);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        //press the button create project on the landing page
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        /////Description//////////
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        //////////CLICK AL CALENDARIO///////////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getCalendarIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Start Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////////Select day of start day///////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDay())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select start day", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////////Select end date////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDate())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDateCalendar())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on End Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDayE())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select end date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);

        //Wait 10 seconds for an alert
        Wait wait2 = new FluentWait(driver)
                .withTimeout(5, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(Exception.class);

        Assert.assertTrue(pc.getdescError().isDisplayed(), "Does not display a message // ");
    }


    //////////////////////////////////////////////////SCENARIO 10////////////////////////////////////////////////////////////////////////
    @Test(groups = {"functest"} , priority =10)
    public void scenario10() throws IOException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 10", "Try to put a description with fewer characters than allowed");
        dataArray = fr.turnArray(user, 10);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        //press the button create project on the landing page
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        /////Description//////////
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        Assert.assertTrue(Integer.parseInt(pc.getMinLengthDesc())==20,"Does not accept 20 characters.");

    }

    //////////////////////////////////////////////////SCENARIO 11////////////////////////////////////////////////////////////////////////
    @Test(groups = {"functest"} , priority =11)
    public void scenario11() throws IOException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 11", "Try to put a description with more characters than allowed");
        dataArray = fr.turnArray(user, 11);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        //press the button create project on the landing page
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        /////Description//////////
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        Assert.assertTrue(Integer.parseInt(pc.getMaxLengthDesc())==1024,"Does not accept 1024 characters.");

    }


    //////////////////////////////////////////////////SCENARIO 12////////////////////////////////////////////////////////////////////////
    @Test(groups = {"functest"} , priority =12)
    public void scenario12() throws IOException, InterruptedException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 12", "Create a project without end date");
        dataArray = fr.turnArray(user, 12);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        //press the button create project on the landing page
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        /////Description//////////
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        //////////CLICK AL CALENDARIO///////////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getCalendarIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Start Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        ////////////Select day of start day///////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDay())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select start day", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(!pc.getCheckBox().isSelected()&&!pc.getEndDateCalendar().isEnabled(),"The fields are enabled or selected. //");
        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);

        //Wait 10 seconds for an alert
        Wait wait2 = new FluentWait(driver)
                .withTimeout(5, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(Exception.class);

        Assert.assertTrue(driver.switchTo().alert().getText().contains("Project created succesfully"),"The project is not created. //");
        driver.switchTo().alert().accept();

    }


    /////////////////////////////////////////////SCENARIO 13/////////////////////////////////////////////////////
    @Test(groups = {"functest"},priority = 13)
    public void scenario13() throws IOException, InterruptedException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 13", "the user doesn't enter a start date");
        dataArray = fr.turnArray(user, 13);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];

        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        /////////////press the button create project on the landing page///////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        /////Description//////////
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        pc.getStartDate().click();

        Assert.assertTrue(pc.getdateError().isDisplayed());

    }
    ////////////////////Scenario14////////////////////////////////////
    @Test(groups = {"functest"},priority = 14)
    public void scenario14() throws IOException, InterruptedException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 14", "Try to put an end date that is before the start date");
        dataArray = fr.turnArray(user, 14);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];

        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        /////////////press the button create project on the landing page///////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        /////Description//////////
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //////////CLICK AL CALENDARIO///////////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getCalendarIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Start Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////////Select day of start day///////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDay())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        ////////////Select end date////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDate())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDateCalendar())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDayE())).sendKeys(Keys.ARROW_LEFT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        logger.pass("Alert deployed");
        fr.checkAlert(driver);

        Assert.assertFalse(false,"The project should be not created end date is less than the star date //");

    }
    /////////////////////////////////////////////SCENARIO 15/////////////////////////////////////////////////////
    @Test(groups = {"functest"},priority = 15)
    public void scenario15() throws IOException, InterruptedException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 15", "the user doesn't fill any field");
        dataArray = fr.turnArray(user, 15);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];

        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        /////////////press the button create project on the landing page///////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());

        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        pc.getStartDate().click();

        Assert.assertTrue(pc.getnameError().isDisplayed());

    }
    /////////////////////////////////////////////SCENARIO 16/////////////////////////////////////////////////////
    @Test(groups = {"functest"},priority = 16)
    public void scenario16() throws IOException, InterruptedException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 16", "Cancel project creation");
        dataArray = fr.turnArray(user, 16);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];

        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        /////////////press the button create project on the landing page///////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        ////////Click on the "X"////////////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getcloseButton())).click();
        Assert.assertTrue(pc.getNewProject().isDisplayed());

    }
    //////////////////////Scenario 17///////////////////////
    @Test(groups = {"functest"},priority = 17)
    public void scenario17() throws IOException, InterruptedException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 17", "the user try to create a new project with an already project name");
        dataArray = fr.turnArray(user, 17);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        /////////////press the button create project on the landing page///////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        /////Description//////////
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //////////CLICK AL CALENDARIO///////////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getCalendarIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Start Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////////Select day of start day///////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDay())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        ////////////Select end date////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDate())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDateCalendar())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDayE())).sendKeys(Keys.ARROW_RIGHT,Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        Assert.assertTrue(pc.getNewProject().isDisplayed());
    }
    //////////////////////////Scenario 18/////////////////////////
    @Test(groups = {"functest"},priority = 18)
    public void scenario18() throws IOException, InterruptedException {
        ProjectCreation pc = new ProjectCreation(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        logger = extent.createTest("Scenario 18", "Create a project and assign a member and delete the member entered into the project");
        dataArray = fr.turnArray(user, 18);
        username = dataArray[0];
        password = dataArray[1];
        name = dataArray[2];
        description = dataArray[3];
        start_date = dataArray[4];
        end_date = dataArray[5];
        role = dataArray[6];
        usernameRole = dataArray[7];
        fr.login(username, password, driver);
        temp = fr.getScreenshot(driver);
        /////////////press the button create project on the landing page///////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getInitialCreateProject())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create new project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Assert.assertTrue(pc.getPageTitle().isDisplayed());
        ///////////Name of the project///////////////////////////////////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getName())).sendKeys(name);
        pc.getName().sendKeys(Keys.TAB);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter a name of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        /////Description//////////
        pc.getDescription().sendKeys(description);
        temp = fr.getScreenshot(driver);
        logger.pass("Enter the description of the project", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        pc.getDescription().sendKeys(Keys.TAB);
        /////////Click on roles///////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getRole())).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select a roll", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ///////////////Select member/////////////////
        pc.getUsername().sendKeys(usernameRole);
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getAddButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Select a member", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        /////////////Click on the list of member and delete/////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getMemberList())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getDeleteMember())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Select a member", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //////////CLICK AL CALENDARIO///////////////////
        varWat.until(ExpectedConditions.visibilityOf(pc.getCalendarIcon())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on Start Date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////////Select day of start day///////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDay())).sendKeys(Keys.ARROW_RIGHT, Keys.ENTER);
        ////////////Select end date////////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDate())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getEndDateCalendar())).click();
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getChangeDayE())).sendKeys(Keys.ARROW_RIGHT, Keys.ENTER);
        temp = fr.getScreenshot(driver);
        logger.pass("Select date", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        ////////click create project/////////
        varWat.until(ExpectedConditions.elementToBeClickable(pc.getCreateButton())).click();
        temp = fr.getScreenshot(driver);
        logger.pass("Click on create button", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        fr.isAlertPresent(driver);
        Assert.assertFalse(false,"The project was created when a member was deleted //");

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
        fr.checkAlert(driver);
        extent.flush();
        driver.close();
    }
}



