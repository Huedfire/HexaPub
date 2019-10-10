/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Training
 */
public class BancaNet {

    public static void main(String[] args) {

        WebDriver driver;
        WebElement element;
        System.setProperty("webdriver.chrome.driver", "c://selenium//chromedriver.exe");

        driver = new ChromeDriver();
        //Maximizar el navegador
        driver.manage().window().maximize();

        //wats implicitos y explicitos
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait varWait = new WebDriverWait(driver, 20);

        //Responsive Web Testing
        try {
            driver.get("https://www.banamex.com/resources/bancanets/bne/esp/DemoBNE/index_m.html");

            element = driver.findElement(By.xpath(
                    "//**[@value=\"ENTRAR\"][2]"));
            
            varWait.until(ExpectedConditions.elementToBeClickable(element));

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
