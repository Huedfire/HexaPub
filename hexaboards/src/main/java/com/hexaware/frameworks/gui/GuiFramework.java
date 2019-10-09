package com.hexaware.frameworks.gui;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.hexaware.frameworks.gui.pageobjects.HomePage;
import com.hexaware.frameworks.gui.pageobjects.Login;
import com.hexaware.frameworks.gui.pageobjects.UserRegistration;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class GuiFramework {


    public String[] turnArray(ArrayList<String> list, int index) {
        String[] array = list.get(index).split(",");
        for (int i=0; i<array.length;i++){
            array[i]=array[i].substring(1);
        }
        return array;
    }


    //method to get data from a Excel in a list
    public ArrayList<String> readExcel(String filepath, int sheet) throws IOException {
        Workbook workbook = WorkbookFactory.create(new File(filepath));
        ArrayList<String> list = new ArrayList();
        ArrayList<String> list2 = new ArrayList();

        Sheet sheets = workbook.getSheetAt(sheet);
        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 1. You can obtain a rowIterator and columnIterator and iterate over them
        // System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheets.rowIterator();


        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Now let's iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                String cellValue = dataFormatter.formatCellValue(cell);
                //System.out.print(cellValue + "\t");

                list.add(cellValue);


            }
            String str = list.toString();
            list2.add(str.substring(0, str.length() - 1));


            list.clear();


        }


        return list2;
    }


    // Method to define a webdriver
    public WebDriver initDriver(Properties props) throws IOException {
        WebDriver drivers = null;
        props = new Properties();
        InputStream inputs = new FileInputStream("C:\\Users\\Training\\HexaboardAutomationTest\\hexaboards\\confs.txt");
        props.load(inputs);
        String browser = props.getProperty("browser");
        String driver = props.getProperty("driver");
        String path = props.getProperty("path");
        int waittime = Integer.parseInt(props.getProperty("implicitWaitTime"));

        if (browser.equals("chrome")) {
            System.setProperty(driver, path);
            drivers = (WebDriver) new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty(driver, path);
            drivers = (WebDriver) new FirefoxDriver();
        } else if (browser.equals("ie")) {
            System.setProperty(driver, path);
            drivers = (WebDriver) new InternetExplorerDriver();
        }
        drivers.manage().timeouts().implicitlyWait(waittime, TimeUnit.SECONDS);
        return drivers;
    }


    //method to make and get screenshots
    public static String getScreenshot(WebDriver driver) {
        TakesScreenshot ts = (TakesScreenshot) driver;

        File src = ts.getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir") + "\\Screenshot\\" + System.currentTimeMillis() + ".png";

        File destination = new File(path);

        try {
            FileHandler.copy(src, destination);
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            System.out.println("Capture Failed " + e.getMessage());
        }

        return path;
    }

    public void checkAlert(WebDriver driver) {
        WebDriverWait wait;
        try {
            //Wait 2 seconds for an alert
            wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.alertIsPresent());
            //Accepts de alert
            Alert alert = driver.switchTo().alert();
            alert.accept();
            //Wait 2 seconds
            wait = new WebDriverWait(driver, 2);
        } catch (Exception e) {
        }



    }
    // Method that check if one alert exist in this page
    public boolean isAlertPresent(WebDriver driver) {

        boolean presentFlag = false;

        try {

            // Check the presence of alert
            Alert alert = driver.switchTo().alert();
            // Alert present; set the flag
            presentFlag = true;
            // if present consume the alert
            alert.accept();

        } catch (NoAlertPresentException ex) {
            // Alert not present
            ex.printStackTrace();
        }

        return presentFlag;

    }

    public void login(String username, String password, WebDriver driver)throws IOException{
        HomePage hp = new HomePage(driver);
        Login lg = new Login(driver);
        hp.getStartedValue().click();
        //press the button login
        lg.getLogin().sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //element name
        lg.getUsername().sendKeys(username);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //get password
        lg.getPassword().sendKeys(password);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //click to login
        lg.getloginButton().click();;
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }

    public void UserRegistration(String name, String email, String username, String password, String confpass, WebDriver driver)
    {
        WebDriverWait varWat = new WebDriverWait(driver, 10);

        HomePage hp = new HomePage(driver);
        UserRegistration ur = new UserRegistration(driver);

        //Step 1
        varWat.until(ExpectedConditions.elementToBeClickable(hp.getStartedValue())).click();
        //Step 2
        ur.getRegister().sendKeys(Keys.ARROW_DOWN);
        ur.getRegister().sendKeys(Keys.ENTER);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 3
        ur.getName().sendKeys(name);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 4
        ur.getEmail().sendKeys(email);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 5
        ur.getUsername().sendKeys(username);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 6
        ur.getPassword().sendKeys(password);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //Step 7
        ur.getConfirmPassword().sendKeys(confpass);
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
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        //step 9
        ur.getButtonCreate().click();

        driver.switchTo().alert().accept();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);


    }



}
