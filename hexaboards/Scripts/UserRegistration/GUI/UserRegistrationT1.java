package UserRegistration.GUI;

import Framework.ExcelReader;
import Framework.HomePage;
import Framework.UserRegistration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class UserRegistrationT1 {

    //Main method that execute the script
    public static void main(String[] args) throws IOException {

        // Read Properties File
        Properties prop = new Properties();
        InputStream input = new FileInputStream("C:\\Users\\Training\\Documents\\conf.txt");
        // load a properties file
        prop.load(input);
        String filepath = prop.getProperty("DataFile");
        String chromeprop = prop.getProperty("ChromeDriver");
        String URI = prop.getProperty("URI");

        //Declare variables used in this script/////////////////////////////////////////////////////////////////////////
        WebDriver driver = null;
        WebElement element;
        ExcelReader r = new ExcelReader();
        ArrayList<String> user = r.readRows(filepath, 1);
        System.setProperty("webdriver.chrome.driver", chromeprop);
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--start-maximized");
        driver = new ChromeDriver(option);
        HomePage hp = new HomePage(driver);
        UserRegistration usr = new UserRegistration(driver);
        WebDriverWait varWat = new WebDriverWait(driver, 10);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Separe the list of elements in one array and make each test////////////////////////////////////////////////
        for (int i = 1; i < user.size(); i++) {
            String[] parts = user.get(i).split(",");

            String Name = parts[0];
            String Email = parts[1].substring(1);
            String Username = parts[2].substring(1);
            String Password = parts[3].substring(1);
            String ConfPassword = parts[4].substring(1);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // Test Script///////////////////////////////////////////////////////////////////////////////////////////////////

            try {


                //Open the Hexaboard site
                driver.navigate().to(URI);

                //Manage Hexaboard HomePage

                //press the button get started value
                element = hp.getStartedButton();
                varWat.until(ExpectedConditions.elementToBeClickable(element));
                element.click();


                // Calling the process method that make all the steps for the user registration/////////////////////////
                Process(element, usr, Name, Email, Username, Password, ConfPassword);

                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);


                //press accept to alert
                checkAlert(driver);

                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

                // close and quit driver
                // driver.quit();
                //driver.close();

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }


        }


    }


    // Method that execute all the scenarios of this test case

    public static void Process(WebElement element, UserRegistration usr, String name, String User, String email, String pass, String conf) {

        //Type the name in the field
        element = usr.getName();
        element.sendKeys(name);

        //Type the email in the field
        element = usr.getEmail();
        element.sendKeys(User);

        //Type the username in the field
        element = usr.getUsername();
        element.sendKeys(email);

        //Type the password in the field
        element = usr.getPassword();
        element.sendKeys(pass);

        //Type the confirm password field
        element = usr.getConfirmPassword();
        element.sendKeys(conf);

        //locate the terms checkbox and make click
        element = usr.getTermsLocator();
        element.click();

        //locate the button create and make a click
        element = usr.getButtonCreate();
        element.click();


    }


    // Method that check if one alert exist in this page
    public static boolean isAlertPresent(WebDriver driver) {

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

    public static void checkAlert(WebDriver driver) {
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



}
