package ProfileInformations;

import Framework.EditProfile;
import Framework.ExcelReader;
import Framework.Login;
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

public class ProfileInformationT2 {
    public static void main(String args[]) throws IOException {
        try {
            // Read Properties File
            Properties prop = new Properties();
            InputStream input = new FileInputStream("C:\\Users\\Training\\Documents\\conf.txt");
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
            String username;
            String password;

            //Assign driver options
            System.setProperty("webdriver.chrome.driver", chromeprop);
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--start-maximized");

            //separe the elements of login that will use
            String[] parts = user.get(1).split(",");
            username = parts[0];
            password = parts[1].substring(1);

            //initialize driver option and create instances
            driver = new ChromeDriver(option);
            Login log = new Login(driver);
            EditProfile ep = new EditProfile(driver);

            //open navigator
            driver.navigate().to(URI);

            //call login method process
            login(driver,element,log,username,password);
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
            //call editname process
            editName(driver,element,ep);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void login(WebDriver driver,WebElement element, Login log, String Username, String Password) {

        WebDriverWait varWat = new WebDriverWait(driver, 20);
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

    public static void editName(WebDriver driver, WebElement element, EditProfile ep){
        WebDriverWait varWat = new WebDriverWait(driver, 20);

        //open the profile information
        element = ep.getProfilePhoto();
        varWat.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        varWat.until(ExpectedConditions.elementToBeClickable(element));

        //close the profile information
        element = ep.getCloseEdit();
        element.click();


    }
}
