package EditProfile;

import Framework.EditProfile;
import Framework.ExcelReader;
import Framework.Login;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Password2 {
    public static void main(String args[]) throws IOException {

        try {
            // Read Properties File
            Properties prop = new Properties();
            InputStream input = new FileInputStream("C:\\Users\\Training\\Desktop\\conf.txt");

            //Load a properties file
            prop.load(input);
            String filepath = prop.getProperty("DataFile");
            String chromeprop = prop.getProperty("ChromeDriver");
            String URI = prop.getProperty("URI");

            //Variables
            WebDriver driver = null;
            WebElement element = null;
            ExcelReader r = new ExcelReader();
            ArrayList<String> user = r.readRows(filepath, 0);
            ArrayList<String> edit = r.readRows(filepath, 6);
            String newPassword;
            String username;
            String password;

            //Assign driver options
            System.setProperty("webdriver.chrome.driver", chromeprop);
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--start-maximized");

            //Save in a arraylist the username and password to login in the application
            String[] parts1 = user.get(1).split(",");
            username = parts1[0];
            password = parts1[1].substring(1);

            //Make a cycle to run all the test scenarios
            for (int i = 1; i < edit.size(); i++) {

                //Save in a arraylist all the names to use in the scripts
                String[] parts2 = edit.get(i).split(",");
                newPassword = parts2[0];

                //Assign driver option required for the test
                //Open a new navigator
                driver = new ChromeDriver(option);
                Login log = new Login(driver);
                EditProfile ep = new EditProfile(driver);

                //Naviigate to the web site
                driver.navigate().to(URI);

                //Login in the web site
                login(driver,element,log,username,password);

                //Wait 30 seconds
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

                //Edit user's name
                editPassword(driver,element,ep,newPassword);

            }


        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }


    public static void login(WebDriver driver,WebElement element, Login log, String Username, String Password) {

        WebDriverWait varWat = new WebDriverWait(driver, 40);

        //press the button get started value
        element = log.getStartedValue();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();

        //get button login
        element = log.getLogin();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(Keys.ENTER);

        //element name
        element = log.getUsername();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(Username);

        //get password
        element = log.getPassword();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(Password);

        //click to login
        element = log.getloginButton();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }


    public static void editPassword(WebDriver driver, WebElement element, EditProfile ep, String name){
        WebDriverWait varWat = new WebDriverWait(driver, 40);

        //Click on the profile photo
        element = ep.getProfilePhoto();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();

        //Click on change password
        element = ep.getChangePassword();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();

        checkAlert(driver);

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

            //Refresh page
            driver.navigate().refresh();

        } catch (Exception e) {

        }
    }

}
