package com.hexaware.testscripts.ProjectSetup;

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
    String[] dataArrayL;
    ArrayList<String> usernameL;
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

     /*
    @Test(groups = {"functest"}, priority = 2)
    public void scenario2() throws IOException, InterruptedException {
        logger = extent.createTest("Project Setup Scenario 2", "A description with 1025 characters");

        dataArray = fr.turnArray(projsetup, 2);
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


    @Test(groups = {"functest"}, priority = 3)
    public void scenario3() throws IOException, InterruptedException {
        logger = extent.createTest("Project Setup Scenario 3", "A description with 1024 characters");


        dataArray = fr.turnArray(projsetup, 3);
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


    @Test(groups = {"functest"}, priority = 4)
    public void scenario4() throws IOException, InterruptedException {
        logger = extent.createTest("Project Setup Scenario 4", "A description with special characters");

        dataArray = fr.turnArray(projsetup, 4);
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


    @Test(groups = {"functest"}, priority = 5)
    public void scenario5() throws IOException, InterruptedException {
        logger = extent.createTest("Project Setup Scenario 5", "A description with numbers");

        dataArray = fr.turnArray(projsetup, 5);
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

    @Test(groups = {"functest"}, priority = 6)
    public void scenario6() throws IOException, InterruptedException {
        logger = extent.createTest("Project Setup scenario 6", "A description with a text field in blank");

        dataArray = fr.turnArray(projsetup, 6);
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

        boolean x = false;
        //check alert
        if(fr.isAlertPresent(driver)){
            //Assert
            Assert.fail("The description was changed. //");
            x = true;
            //check alert
            driver.switchTo().alert().accept();
            //Refresh page
            driver.navigate().refresh();
        }
        else{
            Assert.assertTrue(x==false,"Does not display a message.  //");

        }

    }

//////////////////////////////////Sprint name scripts////////////////////////////////////////////////////////////////

    @Test(groups = {"functest"}, priority = 7)
    public void scenario7() throws IOException, InterruptedException {
        logger = extent.createTest("Project Setup Scenario 7", "A sprint with a name already used");

        dataArrayS = fr.turnArray(nameSprint, 1);
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
        Assert.assertTrue(driver.switchTo().alert().getText().contains("Name already in use"),"Does not display a message.  //");
        //check alert
        driver.switchTo().alert().accept();
        //Refresh page
        driver.navigate().refresh();

    }

      */


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
    /*

    @Test(groups = {"functest"}, priority = 9)
    public void scenario9() throws IOException, InterruptedException {
        logger = extent.createTest("Project Setup Scenario 9", "A sprint with a name that contains special characters");

        dataArrayS = fr.turnArray(nameSprint, 3);
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


    @Test(groups = {"functest"},priority = 10)
    public void scenario10() throws IOException, InterruptedException {
        logger = extent.createTest("Project Setup Scenario 10", "A sprint with a name that contains only numbers");

        dataArrayS = fr.turnArray(nameSprint, 4);
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


    @Test(groups = {"functest"}, priority = 11)
    public void scenario11() throws IOException, InterruptedException {
        logger = extent.createTest("Project Setup Scenario 11", "A sprint with a name field in blank");

        dataArrayS = fr.turnArray(nameSprint, 5);
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

        boolean x = false;
        //check alert
        if(fr.isAlertPresent(driver)){
            //Assert
            Assert.fail("The description was changed. //");
            x = true;
            //check alert
            driver.switchTo().alert().accept();
            //Refresh page
            driver.navigate().refresh();
        }
        else{
            Assert.assertTrue(x==false,"Does not display a message.  //");

        }


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



