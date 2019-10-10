/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Training
 */
public class OrbitzTravel {

    public static void main(String[] args) {

        WebDriver driver;
        WebElement element;
        JavascriptExecutor js;

        System.setProperty("webdriver.chrome.driver", "c://selenium//chromedriver.exe");

        ChromeOptions option = new ChromeOptions();
        option.addArguments("--start-maximized");
        driver = new ChromeDriver(option);
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();

        try {

            driver.navigate().to("https://www.orbitz.com/");

            //Pagina de Inicio
            element = driver.findElement(By.id("package-origin-hp-package"));
            element.sendKeys("Monterrey, Nuevo Leon, Mexico (MTY-General Mariano Escobedo Intl.)");
            driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
            element = driver.findElement(By.id("package-destination-hp-package"));

            element.sendKeys("Mexico City, Distrito Federal, Mexico");

            element = driver.findElement(By.id("package-departing-hp-package"));
            element.sendKeys("09/15/2019");

            element = driver.findElement(By.id("package-returning-hp-package"));
            element.sendKeys("09/18/2019");

            element = driver.findElement(By.id("package-rooms-hp-package"));
            element.sendKeys("2");

            element = driver.findElement(By.id("package-1-adults-hp-package"));
            element.sendKeys("1");
            element = driver.findElement(By.id("package-1-children-hp-package"));
            element.sendKeys("2");

            element = driver.findElement(By.id("package-1-age-select-1-hp-package"));
            element.sendKeys("12");
            element = driver.findElement(By.id("package-1-age-select-2-hp-package"));
            element.sendKeys("7");

            element = driver.findElement(By.id("package-2-adults-hp-package"));
            element.sendKeys("1");
            element = driver.findElement(By.id("package-2-children-hp-package"));
            element.sendKeys("1");
            element = driver.findElement(By.id("package-2-age-select-1-hp-package"));
            element.sendKeys("10");

            element = driver.findElement(By.id("package-advanced-preferred-class-hp-package"));
            element.sendKeys("Economy/Coach");

            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300)");

            element = driver.findElement(By.id("search-button-hp-package"));
            element.click();

            Thread.sleep(20000);

            driver.findElement(By.cssSelector("#resultsContainer article:nth-child(1) a[href*=\"https\"]")).click();

            String currentWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();
            for (String window : allWindows) {

                // driver.switchTo().window(window);
                if (!currentWindow.equals(window)) {
                    driver.switchTo().window(window);
                }
            }
            Thread.sleep(10000);

            driver.findElement(By.cssSelector(" #rooms-and-rates > article:nth-child(2) a[role*=\"button\"]")).click();

            Thread.sleep(2000);

            //driver.findElement(By.cssSelector("#flightModuleList:nth-child(1) .btn-secondary")).click();
            driver.findElement(By.xpath("")).click();
            //

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
