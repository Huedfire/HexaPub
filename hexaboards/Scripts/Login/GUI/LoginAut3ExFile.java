package LoginAutomation;

import Framework.ExcelReader;
import Framework.HomePage;
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

public class LoginAut3ExFile {


        //Main method that execute the script
        public static void main(String[] args) throws IOException {

            // Read Properties File
            Properties prop = new Properties();
            InputStream input = new FileInputStream("C:\\Users\\Training\\IdeaProjects\\HexaboardAutomationTest\\Hexaboard\\conf.txt");
            // load a properties file
            prop.load(input);
            String filepath = prop.getProperty("DataFile");
            String chromeprop = prop.getProperty("ChromeDriver");
            String URI = prop.getProperty("URI");

            //Declare variables used in this script/////////////////////////////////////////////////////////////////////////
            WebDriver driver = null;
            WebElement element;
            ExcelReader r = new ExcelReader();
            ArrayList<String> user = r.readRows(filepath, 0);
            System.setProperty("webdriver.chrome.driver", chromeprop);
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--start-maximized");
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            //Separe the list of elements in one array and make each test////////////////////////////////////////////////
            for (int i = 1; i < user.size(); i++) {
                int j = 1;
                String[] parts = user.get(i).split(",");

                String Username = parts[0];
                String Password = parts[1].substring(1);
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                // Asigning the driver options for each test////////////////////////////////////////////////////////////////////
                driver = new ChromeDriver(option);
                HomePage hp = new HomePage(driver);
                Login lg = new Login(driver);

                WebDriverWait varWat = new WebDriverWait(driver, 10);

                // Test Script/////////////////////////////////////////////////////////////////////////////////////////////////
                try {
                    //Open the Hexaboard site
                    driver.navigate().to(URI);

                    //Manage Hexaboard HomePage

                    //press the button get started value
                    element = hp.getStartedButton();
                    varWat.until(ExpectedConditions.elementToBeClickable(element));
                    element.click();


                    // Calling the process method that make all the steps for the Login/////////////////////////
                    Process(element, lg, Username, Password,hp);

                    driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }


        }

        // Method that execute all the scenarios of this test case
        public static void Process(WebElement element, Login lg, String Username, String Pass,HomePage hp) {

            //press the button getting started
            element = lg.getStartedValue();
            element.click();

            //press the button login
            element = lg.getLogin();
            element.sendKeys(Keys.ENTER);

            //element name
            element = lg.getUsername();
            element.sendKeys(Username);

            //get password
            element = lg.getPassword();
            element.sendKeys(Pass);

            //click to login
            element = lg.getloginButton();
            element.click();


        }

    }
